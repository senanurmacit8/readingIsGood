package com.book.readingIsGood.repository;

import com.book.readingIsGood.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User,String>{

    User findByUsername(String username);

}
