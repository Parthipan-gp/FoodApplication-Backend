package com.niit.FavoriteService.Repository;

import com.niit.FavoriteService.Domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FavoriteRepository extends MongoRepository<User,String> {
}
