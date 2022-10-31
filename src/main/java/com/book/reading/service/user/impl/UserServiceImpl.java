package com.book.reading.service.user.impl;

import com.book.reading.dto.user.UserInfo;
import com.book.reading.mapper.UserMapper;
import com.book.reading.repository.UserRepository;
import com.book.reading.service.user.UserService;
import com.mongodb.MongoException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private UserMapper mapper;

    public UserServiceImpl(UserRepository repository,
                           UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ResponseEntity<String> userRegister(UserInfo userInfo) throws MongoException {
        log.info("userRegister method called. ");
        try {
            repository.save(mapper.mapToUser(userInfo));
        } catch (MongoException er) {
            log.error(er);
            throw new MongoException("save operation is failed.");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
