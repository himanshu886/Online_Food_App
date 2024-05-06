package com.himanshu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.himanshu.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
 
		public User findByEmail (String username);
}
