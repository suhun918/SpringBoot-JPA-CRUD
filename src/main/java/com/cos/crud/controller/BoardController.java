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

		return "/board/writeForm";

	}

	@PostMapping("/board/write")
	public String boardWrite(Board board, HttpSession session) {
		User user = (User) session.getAttribute("user");
		// board에 유저정보 넣고 세이브해줘야한다.
		board.setUser(user);
		mRepo.save(board);
		return "redirect:/board/list";

	}

	@DeleteMapping("/board/delete/{id}")
	public String boardDelete(@PathVariable int id) {

		mRepo.deleteById(id);
		return "redirect:/board/list";
	}

	@GetMapping("/board/updateForm/{id}")
	public String boardUpdateForm(@PathVariable int id, Model model) {

		Optional<Board> board = mRepo.findById(id);
		model.addAttribute("board", board.get());
		return "board/updateForm";
	}

	@PutMapping("/board/update")
	public String boardUpdate(Board board) {

		mRepo.save(board);
		return "redirect:/board/list";
	}

}
