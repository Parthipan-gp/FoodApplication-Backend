package com.niit.FavoriteService.Service;

import com.niit.FavoriteService.Domain.Dish;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        System.out.println(" service layer invoked");

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

            for (int i=0;i<returnedUser.getRestaurantList().size();i++){
                if (returnedUser.getRestaurantList().get(i).getRestaurantName().equals(restaurant.getRestaurantName())){
                    throw new RestaurantAlreadyExists();
                }
            }
           List<Restaurant> returnedUserRestaurantList= returnedUser.getRestaurantList();

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

    @Override
    public User getUserById(String userEmail) throws UserNotFoundException {

        if(favoriteRepository.findById(userEmail).isEmpty()){
            throw new UserNotFoundException();
        }

        return favoriteRepository.findById(userEmail).get();
    }

    @Override
    public Dish getDishById(int restaurantId,int dishId, String userEmail) throws UserNotFoundException {

        if (favoriteRepository.findById(userEmail).isEmpty()){
            throw new UserNotFoundException();
        }

        User returnedUserObject= favoriteRepository.findById(userEmail).get();

       List<Restaurant> existingRestaurantList= returnedUserObject.getRestaurantList();

       for(int i =0;i<existingRestaurantList.size();i++){ //since we have list of restaurants for 1 user , we have to match the  incoming restaurant id from the list of restaurants
           if(existingRestaurantList.get(i).getRestaurantId().equals(restaurantId)){   // filtering out 1 restaurant which has list of dishes
              List<Dish> existingDishListForOneRestaurant= existingRestaurantList.get(i).getDishList();   // existingRestaurantList.get(i) means when i=0(i.e ), it denotes that particular restaurant id we are retreiving the dishlist  and storing it in a variable(List<Dish>)
              for (int j=0;j<existingDishListForOneRestaurant.size();j++) { //since we have list of dishes for one restaurant ,so we have to match the incoming Dishid from the list od dishes
                  if (existingDishListForOneRestaurant.get(j).getDishId().equals(dishId)) {
                      return existingDishListForOneRestaurant.get(j); // retreiving that particular dish for the specified restaurant id
                  }
              }
           }
       }
        return null;
    }


}
