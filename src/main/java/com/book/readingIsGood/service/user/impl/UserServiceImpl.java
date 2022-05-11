package com.book.readingIsGood.service.user.impl;

import com.book.readingIsGood.dto.user.UserDTO;
import com.book.readingIsGood.mapper.UserMapper;
import com.book.readingIsGood.repository.UserRepository;
import com.book.readingIsGood.service.user.UserService;
import com.mongodb.MongoInternalException;
import lombok.extern.log4j.Log4j2;
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

    public String userRegister(UserDTO userDTO) throws MongoInternalException {
        log.info("userRegister method called. ");
        try {
            repository.save(mapper.mapToUser(userDTO));
        } catch (MongoInternalException er) {
            log.error(er);
            throw new MongoInternalException("save operation is failed.");
        }

        return "Success";
    }
}
