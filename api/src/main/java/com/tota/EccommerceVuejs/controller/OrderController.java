package com.tota.EccommerceVuejs.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.tota.EccommerceVuejs.DTO.CheckoutItemDTO;
import com.tota.EccommerceVuejs.DTO.StripeResponse;
import com.tota.EccommerceVuejs.service.AuthenticationtokenService;
import com.tota.EccommerceVuejs.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private AuthenticationtokenService authenticationtokenService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkouList(@RequestBody List<CheckoutItemDTO>checkoutItemDTOS) throws StripeException {

        Session session = orderService.createSession(checkoutItemDTOS);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }

}
