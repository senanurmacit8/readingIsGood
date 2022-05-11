package com.book.readingIsGood.service.auth;

import com.book.readingIsGood.dto.user.UserDTO;
import com.book.readingIsGood.mapper.UserMapper;
import com.book.readingIsGood.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Log4j2
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository repository;
    private UserMapper mapper;

    public CustomUserDetailService(UserRepository userRepository,
                                   UserMapper mapper) {
        this.repository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;

        try {
            UserDTO responseUserDTO = mapper.mapToUserDTO(repository.findByUsername(username));

            if (null != responseUserDTO) {
                user = new User(username, responseUserDTO.getPassword(), new ArrayList<>());
            }
        } catch (UsernameNotFoundException ex) {
            log.error("username not found", ex);
        }

        return user;
    }
}
