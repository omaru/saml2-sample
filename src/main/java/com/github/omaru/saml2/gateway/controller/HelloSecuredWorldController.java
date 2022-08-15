package com.github.omaru.saml2.gateway.controller;

import com.github.omaru.saml2.usecase.UseCase;
import com.github.omaru.saml2.usecase.request.HelloSecuredWorldRequest;
import com.github.omaru.saml2.usecase.response.HelloSecuredWorldResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HelloSecuredWorldController {
    private final UseCase<HelloSecuredWorldRequest, HelloSecuredWorldResponse> helloSecuredWorld;

    @GetMapping(value = "hello", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<HelloSecuredWorldResponse> hello(final Principal principal) {
        return ResponseEntity.ok(helloSecuredWorld.execute(HelloSecuredWorldRequest.
                builder().userName(principal.getName()).build()));
    }
}
