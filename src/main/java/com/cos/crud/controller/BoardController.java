package com.cos.crud.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.crud.model.Board;
import com.cos.crud.model.User;
import com.cos.crud.repository.BoardRepository;
import com.cos.crud.utils.Script;

@Controller
public class BoardController {

	@Autowired
	private BoardRepository mRepo;

	@GetMapping("/board/list")
	public String boardlist(Model model) {
		// 게시글은 여러개니까
		List<Board> boards = mRepo.findAll();
		// 모델에 담아야 뷰페이지까지 데이터를 끌고 간다.
		model.addAttribute("boards", boards);
		return "/board/list";
	}

	@GetMapping("/board/detail/{id}")
	public String boardDetail(@PathVariable int id, Model model) {

		// 옵셔널을 달아주면 절대 null이 나오지 않는다.
		Optional<Board> board = mRepo.findById(id);
		// board.get()은 옵셔널 안의 것을 가져옴 그런데 얘는 null일 수도있다
		// 원래 검사로직 짜야하나 여기서는 생략한다.
		model.addAttribute("board", board.get());
		return "board/detail";
	}

	@GetMapping("/board/writeForm")
	public String boardWriteForm(HttpSession session) {
		// 로그인 됐는지 체크 (나중에는 인터셉터처리 AOP 쓸 것)
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return "/board/writeForm";
		} else {
			return "/user/loginForm";
		}

	}

	@PostMapping("/board/write")
	public @ResponseBody String boardWrite(Board board, HttpSession session) {
		User user = (User) session.getAttribute("user");
		//포스트맨 같은 걸로 공격들어올 수 있으므로 여기도 세션 걸어줘야한다.
		if (user != null) {
			//board에 유저정보 넣고 세이브해줘야한다.
			board.setUser(user);
			mRepo.save(board);
			return Script.href("/board/list");
		} else {
			return Script.href("/user/loginForm");
		}

	}

	@DeleteMapping("/board/delete/{id}")
	public String boardDelete(@PathVariable int id) {
		//세션 있어야하는데 생략함
		mRepo.deleteById(id);
		return "redirect:/board/list";
	}

	@GetMapping("/board/updateForm/{id}")
	public String boardUpdateForm(@PathVariable int id, Model model) {
		//세션 있어야하는데 생략함
		Optional<Board> board = mRepo.findById(id);
		model.addAttribute("board", board.get());
		return "board/updateForm";
	}

	@PutMapping("/board/update")
	public String boardUpdate(Board board) {
		//세션 있어야하는데 생략함
		mRepo.save(board);
		return "redirect:/board/list";
	}

}
