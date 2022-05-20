package com.book.readingIsGood.service.user.impl;

import com.book.readingIsGood.dto.user.UserDTO;
import com.book.readingIsGood.mapper.UserMapper;
import com.book.readingIsGood.repository.UserRepository;
import com.book.readingIsGood.service.user.UserService;
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

    public ResponseEntity userRegister(UserDTO userDTO) throws MongoException {
        log.info("userRegister method called. ");
        try {
            repository.save(mapper.mapToUser(userDTO));
        } catch (MongoException er) {
            log.error(er);
            throw new MongoException("save operation is failed.");
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
