package com.tota.EccommerceVuejs.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tota.EccommerceVuejs.DTO.ResponseDTO;
import com.tota.EccommerceVuejs.DTO.SignInDTO;
import com.tota.EccommerceVuejs.DTO.SignInResponseDTO;
import com.tota.EccommerceVuejs.DTO.SignupDTO;
import com.tota.EccommerceVuejs.exceptions.AuthenticationFailException;
import com.tota.EccommerceVuejs.exceptions.CustomException;
import com.tota.EccommerceVuejs.model.AuthenticationToken;
import com.tota.EccommerceVuejs.model.User;
import com.tota.EccommerceVuejs.repository.UserRepo;

import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationtokenService authenticationtokenService;

    @Transactional
    public ResponseDTO signup(SignupDTO signupDTO) {
        if (Objects.nonNull(userRepo.findByEmail(signupDTO.getEmail()))) {
            throw new CustomException("User already present");
        }

        String encryptedpassword = signupDTO.getPassword();

        try {
            encryptedpassword = hashPassword(signupDTO.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = new User(signupDTO.getFirstName(), signupDTO.getLastName(), signupDTO.getEmail(),
                encryptedpassword);

        userRepo.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationtokenService.saveConfirmationToken(authenticationToken);

        ResponseDTO responseDTO = new ResponseDTO("success", "user created successfully");
        return responseDTO;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDTO signIn(SignInDTO signInDTO) {

        User user = userRepo.findByEmail(signInDTO.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("user is not valid");
        }

        try {
            if (!user.getPassword().equals(hashPassword(signInDTO.getPassword()))) {
                throw new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        AuthenticationToken token = authenticationtokenService.getToken(user);
        if (Objects.isNull(token)) {
            throw new CustomException("token is not present");
        }

        return new SignInResponseDTO("Success", token.getToken());
    }
}
