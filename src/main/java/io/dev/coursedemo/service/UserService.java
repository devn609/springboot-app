package io.dev.coursedemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.dev.coursedemo.model.User;
import io.dev.coursedemo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
	
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<User> findPaginated(int pageNum, int pageSize, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return this.userRepository.findAll(pageable);
    }

    public User getUserById(long id) {
    	return userRepository.findById(id).orElse(null);
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }

}
