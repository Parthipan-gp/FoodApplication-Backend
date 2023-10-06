package com.niit.RestaurantService.Service;

import com.niit.RestaurantService.Domain.Restaurant;
import com.niit.RestaurantService.Exception.RestaurantAlreadyExistsException;
import com.niit.RestaurantService.Exception.RestaurantNotFoundException;
import com.niit.RestaurantService.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RestaurantServiceImpl implements IRestaurantService{

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository=restaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) throws RestaurantAlreadyExistsException {

        System.out.println(restaurantRepository.findById((restaurant.getRestaurantId())).isPresent());
        if (restaurantRepository.findById((restaurant.getRestaurantId())).isPresent()){
            throw new RestaurantAlreadyExistsException();
        }

        Restaurant returnedRestaurants= restaurantRepository.save(restaurant);
        return returnedRestaurants ;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {

       List<Restaurant> returnedRestaurants= restaurantRepository.findAll();

        return returnedRestaurants;
    }

    @Override
    public Restaurant getRestaurantForId(int restaurantId) throws RestaurantNotFoundException {
        return restaurantRepository.findById(restaurantId).get();
    }

    @Override
    public boolean deleteRestaurant() throws Exception {

        boolean flag=false;

        restaurantRepository.deleteAll();
        flag=true;

        return  flag;


    }
}
