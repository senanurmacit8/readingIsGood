package com.book.readingIsGood.repository;

import com.book.readingIsGood.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends MongoRepository<User,String>{

    User findByUsername(String username);

}
