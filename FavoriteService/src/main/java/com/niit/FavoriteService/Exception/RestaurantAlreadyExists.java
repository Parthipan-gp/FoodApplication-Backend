package com.niit.FavoriteService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "restaurant already exists")
public class RestaurantAlreadyExists extends Exception{
}
