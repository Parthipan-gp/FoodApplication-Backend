package com.niit.UserAuthenticationService.Service;

import com.niit.UserAuthenticationService.Domain.User;
import com.niit.UserAuthenticationService.Exception.InvalidCredentialsException;
import com.niit.UserAuthenticationService.Exception.UserAlreadyExistsException;
import com.niit.UserAuthenticationService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {


    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User saveUser(User user)throws UserAlreadyExistsException {

        System.out.println("service invoked");

        if (userRepository.findById(user.getUserEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        return  userRepository.save(user);
    }

    @Override
    public User getUserByEmailAndPassword(String userEmail, String userPassword) throws InvalidCredentialsException {

        User returnedUser= userRepository.findByUserEmailAndUserPassword(userEmail,userPassword);

        if (returnedUser==null){
            throw new InvalidCredentialsException();
        }

        return returnedUser;
    }
}
