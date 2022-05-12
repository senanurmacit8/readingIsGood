package com.book.readingIsGood.service.user;

import com.book.readingIsGood.dto.user.UserDTO;
import com.mongodb.MongoInternalException;

public interface UserService {

    String userRegister(UserDTO userDTO) throws MongoInternalException;
}
