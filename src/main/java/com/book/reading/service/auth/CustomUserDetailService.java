package com.book.reading.service.auth;

import com.book.reading.dto.user.UserInfo;
import com.book.reading.mapper.UserMapper;
import com.book.reading.repository.UserRepository;
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
            UserInfo responseUserInfo = mapper.mapToUserDTO(repository.findByUsername(username));

            if (null != responseUserInfo) {
                user = new User(username, responseUserInfo.getPassword(), new ArrayList<>());
            }
        } catch (UsernameNotFoundException ex) {
            log.error("username not found", ex);
            throw new UsernameNotFoundException("username not found");
        }

        return user;
    }
}
