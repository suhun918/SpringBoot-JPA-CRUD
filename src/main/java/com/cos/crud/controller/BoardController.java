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
import com.cos.crud.service.BoardService;
import com.cos.crud.utils.Script;

@Controller
public class BoardController {

	@Autowired
	private BoardService mService;
	
	//트렌젝션 테스트 
	//포스트맨으로 테스트
	@PostMapping("/ict/{id}")
	public @ResponseBody String increaseCountAndTimeUpdate(@PathVariable int id) {
		mService.increaseCountAndTimeUpdate(id);
		return "테스트완료";
	}

	@GetMapping("/board/list")
	public String boardlist(Model model) {
		// 게시글은 여러개니까
		List<Board> boards = mService.boardlist();
		// 모델에 담아야 뷰페이지까지 데이터를 끌고 간다.
		model.addAttribute("boards", boards);
		return "/board/list";
	}

	@GetMapping("/board/detail/{id}")
	public String boardDetail(@PathVariable int id, Model model) {

		// 옵셔널을 달아주면 절대 null이 나오지 않는다.
		Board board = mService.boardDetail(id);
		// board.get()은 옵셔널 안의 것을 가져옴 그런데 얘는 null일 수도있다
		// 원래 검사로직 짜야하나 여기서는 생략한다.
		model.addAttribute("board", board);
		return "board/detail";
	}

	@GetMapping("/board/writeForm")
	public String boardWriteForm(HttpSession session) {

		return "/board/writeForm";

	}

	@PostMapping("/board/write")
	public String boardWrite(Board board, HttpSession session) {
		int result = mService.boardWrite(board, session);
		if(result == 1) {
			return "redirect:/board/list";
		}else {
			return "redirect:/board/writeForm";
		}
		

	}

	@DeleteMapping("/board/delete/{id}")
	public @ResponseBody String boardDelete(@PathVariable int id) {
		int result = mService.boardDelete(id);
		if(result == 1) {
			return Script.href("/board/list");
		}else {
			return Script.back("삭제실패");
		}
		
		
	}

	@GetMapping("/board/updateForm/{id}")
	public String boardUpdateForm(@PathVariable int id, Model model) {

		Board board = mService.boardUpdateForm(id);
		model.addAttribute("board", board);
		return "board/updateForm";
	}

	@PutMapping("/board/update")
	public @ResponseBody String boardUpdate(Board board) {
		
		int result = mService.boardUpdate(board);
		if(result == 1) {
			return Script.href("/board/list");
		}else {
			return Script.back("수정 실패");
		}
		
	}

}
