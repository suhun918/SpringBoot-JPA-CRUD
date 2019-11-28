package com.cos.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.crud.model.User;
import com.cos.crud.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository mRepo;

	public List<User> getUser(int id) {
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			users.add(mRepo.findById(id).get());
		}
		return users;
	}

	// 리턴값이 필요한 것은 User로 변경
	public User userLogin(User user) {
		User u = mRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
		return u;
	}

	// save류는 int로 변경
	// save류는 trycatch걸어준다 실패 할 수도 있으니
	public int userJoin(User user) {
		try {
			mRepo.save(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
