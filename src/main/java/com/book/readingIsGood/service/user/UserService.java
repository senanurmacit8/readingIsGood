package com.book.readingIsGood.service.user;

import com.book.readingIsGood.dto.user.UserDTO;
import com.mongodb.MongoInternalException;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity userRegister(UserDTO userDTO) throws MongoInternalException;
}
