package com.niit.FavoriteService.Controller;

import com.niit.FavoriteService.Domain.Restaurant;
import com.niit.FavoriteService.Domain.User;
import com.niit.FavoriteService.Exception.RestaurantAlreadyExists;
import com.niit.FavoriteService.Exception.RestaurantNotFoundException;
import com.niit.FavoriteService.Exception.UserAlreadyExistsException;
import com.niit.FavoriteService.Exception.UserNotFoundException;
import com.niit.FavoriteService.Service.IFavoriteService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class FavoriteController {

    private IFavoriteService iFavoriteService;



    @Autowired
    public FavoriteController(IFavoriteService iFavoriteService){
        this.iFavoriteService = iFavoriteService;
    }

    @PostMapping("/register")
    ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
        System.out.println("controller layer invoked");

        try{
            User returnedUser= iFavoriteService.registerUser(user);
            return  new ResponseEntity<>(returnedUser, HttpStatus.CREATED);
        }
        catch (UserAlreadyExistsException exception){
            throw new UserAlreadyExistsException();
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/save")
    ResponseEntity<?> saveRestaurant(@RequestBody Restaurant restaurant, HttpServletRequest request) throws UserNotFoundException, RestaurantAlreadyExists {
        System.out.println("controller layer invoked");
        try{
            System.out.println("header"+request.getHeader("Authorization"));
            Claims claims=(Claims) request.getAttribute("claims");
            System.out.println(claims);
            String userEmail= claims.getSubject();
            System.out.println("userEmail :"+userEmail);


            User returnedUser= iFavoriteService.saveListOfRestaurants(restaurant,userEmail);
            return new ResponseEntity<>(returnedUser,HttpStatus.CREATED);
        }
        catch (RestaurantAlreadyExists exception){
            throw new RestaurantAlreadyExists();
        }
        catch(UserNotFoundException exception){
            throw new UserNotFoundException();
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/get")
    ResponseEntity<?> getAllFavoriteRestaurants(HttpServletRequest request) throws Exception{
        System.out.println("controller invoked");
        try{
            System.out.println("header"+request.getHeader("Authorization"));
            Claims claims=(Claims) request.getAttribute("claims");
            System.out.println(claims);
            String userEmail= claims.getSubject();
            System.out.println("userEmail :"+userEmail);

            List<Restaurant> favRestaurant= iFavoriteService.getAllListOfRestaurants(userEmail);
            return new ResponseEntity<>(favRestaurant,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("throw exception",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/user/delete/{restaurantId}")
    ResponseEntity<?> deleteRestaurantList( int restaurantId, HttpServletRequest request) throws UserNotFoundException, RestaurantNotFoundException {

        try{
            System.out.println("header"+request.getHeader("Authorization"));
            Claims claims=(Claims) request.getAttribute("claims");
            String userEmail= claims.getSubject();
            System.out.println("userEmail"+userEmail);

            User returnedUser= iFavoriteService.deleteListOfRestaurant(restaurantId,userEmail);
            return new ResponseEntity<>(returnedUser,HttpStatus.OK);
        }
        catch(UserNotFoundException|RestaurantNotFoundException exception){
            throw new UserNotFoundException();
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/get")
    ResponseEntity<?> getAllUsers() throws  Exception{
        System.out.println("controller layer invoked");
        try{
            List<User> returnedListOfUsers= iFavoriteService.getAllUsers();
            return  new ResponseEntity<>(returnedListOfUsers,HttpStatus.OK);
        }

        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete")
    ResponseEntity<?> deleteAllUser() throws Exception{
        try{
            return new ResponseEntity<>(iFavoriteService.deleteUser(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
