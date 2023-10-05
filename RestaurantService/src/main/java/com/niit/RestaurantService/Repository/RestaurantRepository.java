package com.niit.RestaurantService.Repository;

import com.niit.RestaurantService.Domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant,Integer> {
}
