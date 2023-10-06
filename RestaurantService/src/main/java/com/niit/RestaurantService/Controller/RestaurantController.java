package com.niit.RestaurantService.Controller;

import com.niit.RestaurantService.Domain.Restaurant;
import com.niit.RestaurantService.Exception.RestaurantAlreadyExistsException;
import com.niit.RestaurantService.Exception.RestaurantNotFoundException;
import com.niit.RestaurantService.Service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v3")
public class RestaurantController {


    private IRestaurantService iRestaurantService;
    @Autowired
    public RestaurantController(IRestaurantService iRestaurantService){
        this.iRestaurantService=iRestaurantService;
    }



    @PostMapping("/save")
    ResponseEntity<?> saveRestaurants(@RequestBody Restaurant restaurant) throws RestaurantAlreadyExistsException {
        try{
            Restaurant returnedRestaurant= iRestaurantService.save(restaurant);
            return new ResponseEntity<>(returnedRestaurant, HttpStatus.CREATED);
        }
        catch (RestaurantAlreadyExistsException exception){
            throw new RestaurantAlreadyExistsException();
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/get")
    ResponseEntity<?> getAllRestaurants(){

        List<Restaurant> returnedRestaurants= iRestaurantService.getAllRestaurants();

        return new ResponseEntity<>(returnedRestaurants,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteAllRestaurant() throws Exception {

        try {
            return new ResponseEntity<>(iRestaurantService.deleteRestaurant(),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{restaurantId}")
    ResponseEntity<?> getRestaurantForParticularId(@PathVariable int restaurantId) throws RestaurantNotFoundException{
        try{
            Restaurant returnedRestaurantDataForId= iRestaurantService.getRestaurantForId(restaurantId);
            return new ResponseEntity<>(returnedRestaurantDataForId,HttpStatus.OK);
        }
        catch (RestaurantNotFoundException exception){
            throw new RestaurantNotFoundException();
        }
        catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
