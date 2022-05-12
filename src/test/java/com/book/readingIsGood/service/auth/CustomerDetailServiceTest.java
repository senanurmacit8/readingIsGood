package com.book.readingIsGood.service.auth;

import com.book.readingIsGood.dto.user.UserDTO;
import com.book.readingIsGood.mapper.UserMapper;
import com.book.readingIsGood.model.user.User;
import com.book.readingIsGood.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDetailServiceTest {

    @InjectMocks
    CustomUserDetailService service;
    @Mock
    UserRepository repository;
    @Mock
    UserMapper mapper;

    @Test()
    public void whenLoadUserByUsername_thenReturnSuccessResponse() throws UsernameNotFoundException {
        Mockito.when(repository.findByUsername(anyString()))
                .thenReturn(prepareUser());
        Mockito.when(mapper.mapToUserDTO(any())).thenReturn(expectedUserDTO());

        UserDTO userDTO = expectedUserDTO();
        UserDetails expectedUser = new org.springframework.security.core.userdetails.User(userDTO.getUserName(),
                userDTO.getPassword(), new ArrayList<>());
        UserDetails actualResponse = service.loadUserByUsername("senanur");

        Assert.assertEquals(expectedUser, actualResponse);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void whenLoadUserByUsername_thenThrowsException() throws UsernameNotFoundException {
        Mockito.when(repository.findByUsername(anyString()))
                .thenThrow(new UsernameNotFoundException("username not found"));

        service.loadUserByUsername("senanur");
    }

    private User prepareUser() {
        return new User("id", "username", "password");
    }

    private UserDTO expectedUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("senanur");
        userDTO.setPassword("Senanur123.");
        return userDTO;
    }


}
