package com.book.reading.service.auth;

import com.book.reading.dto.user.UserInfo;
import com.book.reading.mapper.UserMapper;
import com.book.reading.model.user.User;
import com.book.reading.repository.UserRepository;
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

        UserInfo userInfo = expectedUserDTO();
        UserDetails expectedUser = new org.springframework.security.core.userdetails.User(userInfo.getUserName(),
                userInfo.getPassword(), new ArrayList<>());
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

    private UserInfo expectedUserDTO() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("senanur");
        userInfo.setPassword("Senanur123.");
        return userInfo;
    }

    /*
    @Test
public void testCreateRetryWithError() {
    Repository<Entity> mockRepo = Mockito.spy(repository);
    Map<String, Object> studentBody = buildTestStudentEntity();
    Map<String, Object> studentMetaData = new HashMap<String, Object>();
    int noOfRetries = 5;

    Mockito.doThrow(new MongoException("Test Exception"))
            .when(((MongoEntityRepository) mockRepo))
            .internalCreate("student", null, studentBody, studentMetaData, "student");
    Mockito.doCallRealMethod()
            .when(mockRepo)
            .createWithRetries("student", null, studentBody, studentMetaData, "student",
                    noOfRetries);

    try {
        mockRepo.createWithRetries("student", null, studentBody, studentMetaData, "student",
                noOfRetries);
    } catch (MongoException ex) {
        assertEquals(ex.getMessage(), "Test Exception");
    }

    Mockito.verify((MongoEntityRepository) mockRepo, Mockito.times(noOfRetries))
            .internalCreate("student", null, studentBody, studentMetaData, "student");
}
     */


}
