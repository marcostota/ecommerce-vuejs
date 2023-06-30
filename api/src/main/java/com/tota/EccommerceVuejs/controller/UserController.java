package com.tota.EccommerceVuejs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tota.EccommerceVuejs.DTO.ResponseDTO;
import com.tota.EccommerceVuejs.DTO.SignInDTO;
import com.tota.EccommerceVuejs.DTO.SignInResponseDTO;
import com.tota.EccommerceVuejs.DTO.SignupDTO;
import com.tota.EccommerceVuejs.service.UserService;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseDTO signup(@RequestBody SignupDTO signupDTO) {

        return userService.signup(signupDTO);
    }

    @PostMapping("/signin")
    public SignInResponseDTO signIn(@RequestBody SignInDTO signInDTO) throws Exception {
        return userService.signIn(signInDTO);
    }
}
