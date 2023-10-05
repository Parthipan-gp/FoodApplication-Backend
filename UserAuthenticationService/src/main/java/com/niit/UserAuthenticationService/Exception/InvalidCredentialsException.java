package com.niit.UserAuthenticationService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED,reason = "invalid credentials")
public class InvalidCredentialsException extends Exception{
}
