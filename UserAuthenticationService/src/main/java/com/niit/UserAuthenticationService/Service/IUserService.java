package com.niit.UserAuthenticationService.Service;

import com.niit.UserAuthenticationService.Domain.User;
import com.niit.UserAuthenticationService.Exception.InvalidCredentialsException;
import com.niit.UserAuthenticationService.Exception.UserAlreadyExistsException;

public interface IUserService {

    User saveUser(User user) throws UserAlreadyExistsException ;

    User getUserByEmailAndPassword(String userEmail,String userPassword) throws InvalidCredentialsException;

}
