package com.book.readingIsGood.service.user;

import com.book.readingIsGood.dto.user.UserDTO;
import com.book.readingIsGood.mapper.UserMapper;
import com.book.readingIsGood.model.user.User;
import com.book.readingIsGood.repository.UserRepository;
import com.book.readingIsGood.service.user.impl.UserServiceImpl;
import com.mongodb.MongoException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    public void whenUserRegister_thenSuccessResponse() throws MongoException {
        Mockito.when(mapper.mapToUser(Mockito.any())).thenReturn(prepareUser().get(0));
        ResponseEntity actualResponse = service.userRegister(expectedUserDTOList().get(0));

        Assert.assertTrue(actualResponse.getStatusCode().is2xxSuccessful());
    }

    @Test(expected = MongoException.class)
    public void whenUserRegister_thenThrowsException() throws MongoException {
        Mockito.when(mapper.mapToUser(Mockito.any())).thenReturn(prepareUser().get(0));
        Mockito.when(repository.save(Mockito.any())).thenThrow(new MongoException("save operation is failed."));
        service.userRegister(expectedUserDTOList().get(0));

    }

    private List<User> prepareUser() {
        User user = new User("id", "username", "password");
        return Collections.singletonList(user);
    }

    private List<UserDTO> expectedUserDTOList() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("senanur");
        userDTO.setPassword("Senanur123.");
        return Collections.singletonList(userDTO);
    }

}
