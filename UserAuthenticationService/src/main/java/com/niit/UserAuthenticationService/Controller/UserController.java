package com.niit.UserAuthenticationService.Controller;

import com.niit.UserAuthenticationService.Domain.User;
import com.niit.UserAuthenticationService.Exception.InvalidCredentialsException;
import com.niit.UserAuthenticationService.Exception.UserAlreadyExistsException;
import com.niit.UserAuthenticationService.Security.SecurityTokenGenerator;
import com.niit.UserAuthenticationService.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private IUserService iUserService;

    private SecurityTokenGenerator securityTokenGenerator;


    @Autowired
    public UserController(IUserService iUserService,SecurityTokenGenerator securityTokenGenerator){
        this.iUserService=iUserService;
        this.securityTokenGenerator=securityTokenGenerator;
    }


    @PostMapping("/save")
    ResponseEntity<?> registerUser(@RequestBody User user)throws UserAlreadyExistsException {

        System.out.println("controller invoked");

        try{
            User returnedUserFromDatabase= iUserService.saveUser(user);
            return new ResponseEntity<>(returnedUserFromDatabase, HttpStatus.CREATED);
        }
        catch (UserAlreadyExistsException exception){
            throw new UserAlreadyExistsException();
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/login")
    ResponseEntity<?> loginUser(@RequestBody User user) throws InvalidCredentialsException {

    User returnedUser= iUserService.getUserByEmailAndPassword(user.getUserEmail(), user.getUserPassword());

    if (returnedUser==null){
        throw new InvalidCredentialsException();
    }

    Map<String,String> token= securityTokenGenerator.createToken(user);

    return new ResponseEntity<>(token,HttpStatus.OK);
    }

}
