package org.jsp.userbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dao.UserDao;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(userDao.saveUser(user));
		structure.setMessage("User Saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.findById(user.getId());
		if (recUser.isPresent()) {
			User dbUser = recUser.get();
			dbUser.setName(user.getName());
			dbUser.setEmail(user.getEmail());
			dbUser.setPhone(user.getPhone());
			dbUser.setPassword(user.getPassword());
			structure.setData(userDao.saveUser(dbUser));
			structure.setMessage("User Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
		}
		structure.setData(null);
		structure.setMessage("User not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<User>> findAll() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setData(userDao.findAll());
		structure.setMessage("User List");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<User>>(HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> findById(int id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> dbuser = userDao.findById(id);
		if (dbuser.isPresent()) {
			structure.setData(dbuser.get());
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		structure.setData(null);
		structure.setMessage("User not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<Boolean>> deleteById(int id) {
		ResponseStructure<Boolean> structure = new ResponseStructure<>();
		Optional<User> dbuser = userDao.findById(id);
		if (dbuser.isPresent()) {
			structure.setData(true);
			structure.setMessage("User Deleted");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			userDao.deleteById(id);
			return new ResponseEntity<ResponseStructure<Boolean>>(structure, HttpStatus.ACCEPTED);
		}

		structure.setData(false);
		structure.setMessage("Cannot delete User ID is invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Boolean>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<User>>  verifyUser(long phone, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = userDao.verify(phone, password);
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}

		structure.setData(null);
		structure.setMessage("Invalid Phone Number");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.NOT_FOUND);
	}

}
