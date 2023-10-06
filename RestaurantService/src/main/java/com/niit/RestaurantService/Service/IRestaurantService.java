package com.niit.RestaurantService.Service;

import com.niit.RestaurantService.Domain.Restaurant;
import com.niit.RestaurantService.Exception.RestaurantAlreadyExistsException;
import com.niit.RestaurantService.Exception.RestaurantNotFoundException;

import java.util.List;

public interface IRestaurantService {

    Restaurant save(Restaurant restaurant) throws RestaurantAlreadyExistsException;
    List<Restaurant>  getAllRestaurants();
    Restaurant getRestaurantForId(int restaurantId) throws RestaurantNotFoundException;

    boolean deleteRestaurant() throws Exception;
}
