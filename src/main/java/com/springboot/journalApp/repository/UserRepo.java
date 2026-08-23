package com.springboot.journalApp.repository;

import com.springboot.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId>
{
    User findByUserName(String username);

    void deleteUserByUserName(String username);
}
