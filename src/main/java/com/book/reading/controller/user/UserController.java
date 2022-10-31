package com.book.reading.controller.user;

import com.book.reading.dto.user.UserInfo;
import com.book.reading.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author senanurmacit
 * @since 1.1
 * @version 1.1
 */
@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This method created for user registiration
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/userRegister")
    @ResponseBody
    @ApiOperation(value = "User Register ",
            notes = "This method used to register with existing user")
    public ResponseEntity<String> userRegister(@RequestParam UserInfo userInfo) {
        return userService.userRegister(userInfo);
    }
}
