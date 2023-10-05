package com.niit.UserAuthenticationService.Repository;

import com.niit.UserAuthenticationService.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

       User findByUserEmailAndUserPassword(String userEmail,String userPassword);
}
