package com.niit.FavoriteService.Service;

import com.niit.FavoriteService.Domain.Restaurant;
import com.niit.FavoriteService.Domain.User;
import com.niit.FavoriteService.Exception.RestaurantAlreadyExists;
import com.niit.FavoriteService.Exception.RestaurantNotFoundException;
import com.niit.FavoriteService.Exception.UserAlreadyExistsException;
import com.niit.FavoriteService.Exception.UserNotFoundException;
import com.niit.FavoriteService.Proxy.UserProxy;
import com.niit.FavoriteService.Repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FavoriteServiceImpl implements IFavoriteService {

    private FavoriteRepository favoriteRepository;
    private UserProxy userProxy;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, UserProxy userProxy){
        this.favoriteRepository=favoriteRepository;
        this.userProxy=userProxy;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {

        System.out.println("service layer invoked");

        if (favoriteRepository.findById(user.getUserEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }

        User returnedUser= favoriteRepository.save(user);

        if (!returnedUser.getUserEmail().isEmpty()){
            ResponseEntity response= userProxy.registerUser(user);
        }

        return returnedUser;
    }

    @Override
    public List<Restaurant> getAllListOfRestaurants(String userEmail) throws Exception {
        System.out.println("service invoked");
        User returnedUser=favoriteRepository.findById(userEmail).get();

        return returnedUser.getRestaurantList() ;
    }

    @Override
    public User saveListOfRestaurants(Restaurant restaurant, String userEmail) throws UserNotFoundException , RestaurantAlreadyExists {

        System.out.println("service layer invoked");

        if (favoriteRepository.findById(userEmail).isEmpty()){
            throw new UserNotFoundException();
        }


        User returnedUser= favoriteRepository.findById(userEmail).get();

        if (returnedUser.getRestaurantList()==null){
            returnedUser.setRestaurantList(Arrays.asList(restaurant));
        }
        else{
           List<Restaurant> returnedUserRestaurantList= returnedUser.getRestaurantList();

           if(returnedUserRestaurantList.contains(restaurant)){
               throw new RestaurantAlreadyExists();
           }
           returnedUserRestaurantList.add(restaurant);

           returnedUser.setRestaurantList(returnedUserRestaurantList);
        }
        return favoriteRepository.save(returnedUser);
    }

    @Override
    public User deleteListOfRestaurant(int restaurantId, String userEmail) throws UserNotFoundException, RestaurantNotFoundException {

        boolean restaurantIdIsPresent= false;

        if (favoriteRepository.findById(userEmail).isEmpty()){
            throw new UserNotFoundException();
        }

        User returnedUser= favoriteRepository.findById(userEmail).get();

       List<Restaurant> existingRestaurant= returnedUser.getRestaurantList();

       restaurantIdIsPresent=existingRestaurant.removeIf(p->p.getRestaurantId().equals(restaurantId));

       if (!restaurantIdIsPresent){
           throw new RestaurantNotFoundException();
       }

       returnedUser.setRestaurantList(existingRestaurant);

        return favoriteRepository.save(returnedUser);
    }

    @Override
    public List<User> getAllUsers() throws Exception {

        System.out.println("service layer invoked");
        return favoriteRepository.findAll();
    }

    @Override
    public boolean deleteUser() throws Exception {

        boolean flag= false;

        favoriteRepository.deleteAll();
        flag=true;

        return flag;
    }


}