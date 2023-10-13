package com.niit.FavoriteService.Service;

import com.niit.FavoriteService.Domain.Dish;
import com.niit.FavoriteService.Domain.Restaurant;
import com.niit.FavoriteService.Domain.User;
import com.niit.FavoriteService.Exception.RestaurantAlreadyExists;
import com.niit.FavoriteService.Exception.RestaurantNotFoundException;
import com.niit.FavoriteService.Exception.UserAlreadyExistsException;
import com.niit.FavoriteService.Exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IFavoriteService {

    User registerUser(User user) throws UserAlreadyExistsException;

    List<Restaurant> getAllListOfRestaurants(String userEmail) throws Exception  ;

    User saveListOfRestaurants(Restaurant restaurant,String userEmail) throws UserNotFoundException , RestaurantAlreadyExists;

    User deleteListOfRestaurant(int restaurantId, String userEmail) throws UserNotFoundException, RestaurantNotFoundException;

    List<User> getAllUsers() throws Exception;

    boolean deleteUser() throws Exception;

    User getUserById( String userEmail) throws  UserNotFoundException;

    Dish getDishById(int restaurantId,int dishId, String userEmail) throws UserNotFoundException;
}
