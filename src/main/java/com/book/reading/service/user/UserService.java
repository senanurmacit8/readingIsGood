package com.book.reading.service.user;

import com.book.reading.dto.user.UserInfo;
import com.mongodb.MongoInternalException;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity userRegister(UserInfo userInfo) throws MongoInternalException;
}
