package org.jsp.userbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {
		return repository.save(user);
	}

	public User updateUser(User user) {
		return repository.save(user);
	}

	public Optional<User> findById(int id) {
		return repository.findById(id);
	}
	public boolean deleteById(int id) {
		Optional<User> recUser = findById(id);
		if(recUser.isPresent()) {
			return true;
		}
		return false;
	}
	public List<User> findAll(){
		return repository.findAll();
	}
	public Optional<User> verify(long phone, String password){
		return repository.verify(phone, password);
	}
}
