package com.book.readingIsGood.controller.user;

import com.book.readingIsGood.dto.user.UserDTO;
import com.book.readingIsGood.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/userRegister")
    @ResponseBody
    public ResponseEntity userRegister(@RequestParam UserDTO userDTO) {
        return userService.userRegister(userDTO);
    }
}
