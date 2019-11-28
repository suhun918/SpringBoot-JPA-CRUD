package com.cos.crud.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.crud.model.Board;
import com.cos.crud.model.User;
import com.cos.crud.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository mRepo;
	
	//트렌젝션 테스트 
	//트랜젝션이 발동하려면 런타임 오류로 바꿔서 던져줘야 인식하고 커밋하지않고 롤백한다.
	@Transactional
	public void increaseCountAndTimeUpdate(int id) {
		try {
			mRepo.increaseCount(id);
			mRepo.timeUpdate(id);
		} catch (Exception e) {
			//발생한 오류를 런타임오류에 던져주는 것 
			throw new RuntimeException(e);
		}
		
	}

	// DB관련 로직만 서비스에 필요하다
	public List<Board> boardlist() {

		List<Board> boards = mRepo.findAll();
		return boards;

	}

	public Board boardDetail(int id) {

		Optional<Board> board = mRepo.findById(id);
		//옵셔널이라서
		return board.get();
	}

	public int boardWrite(Board board, HttpSession session) {

		try {
			User user = (User) session.getAttribute("user");
			board.setUser(user);
			mRepo.save(board);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int boardDelete(int id) {
		try {
			mRepo.deleteById(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public Board boardUpdateForm(int id) {

		Optional<Board> board = mRepo.findById(id);
		
		return board.get();
	}

	public int boardUpdate(Board board) {

		try {
			mRepo.save(board);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
