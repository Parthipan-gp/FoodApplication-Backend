package com.niit.FavoriteService.Service;

import com.niit.FavoriteService.Domain.Restaurant;
import com.niit.FavoriteService.Domain.User;
import com.niit.FavoriteService.Exception.RestaurantAlreadyExists;
import com.niit.FavoriteService.Exception.RestaurantNotFoundException;
import com.niit.FavoriteService.Exception.UserAlreadyExistsException;
import com.niit.FavoriteService.Exception.UserNotFoundException;

import java.util.List;

public interface IFavoriteService {

    User registerUser(User user) throws UserAlreadyExistsException;

    List<Restaurant> getAllListOfRestaurants(String userEmail) throws Exception  ;

    User saveListOfRestaurants(Restaurant restaurant,String userEmail) throws UserNotFoundException , RestaurantAlreadyExists;

    User deleteListOfRestaurant(int restaurantId, String userEmail) throws UserNotFoundException, RestaurantNotFoundException;

    List<User> getAllUsers() throws Exception;

    boolean deleteUser() throws Exception;

    User getUserById( String userEmail) throws  UserNotFoundException;
}
