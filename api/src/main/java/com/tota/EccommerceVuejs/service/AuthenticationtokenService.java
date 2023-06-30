package com.tota.EccommerceVuejs.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tota.EccommerceVuejs.exceptions.AuthenticationFailException;
import com.tota.EccommerceVuejs.model.AuthenticationToken;
import com.tota.EccommerceVuejs.model.User;
import com.tota.EccommerceVuejs.repository.TokenRepo;

@Service
public class AuthenticationtokenService {

    @Autowired
    private TokenRepo tokenRepo;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepo.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepo.findByUser(user);
    }

    public void authenticate(String token) {
        if (Objects.isNull(token)) {
            throw new AuthenticationFailException("token is not present");
        }
        if (Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException("token is not valid");
        }
    }

    public User getUser(String token) {
        final AuthenticationToken authenticationToken = tokenRepo.findByToken(token);
        if (Objects.isNull(authenticationToken)) {
            return null;
        }
        return authenticationToken.getUser();
    }
}
