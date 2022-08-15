package com.github.omaru.saml2.usecase;

import com.github.omaru.saml2.usecase.request.HelloSecuredWorldRequest;
import com.github.omaru.saml2.usecase.response.HelloSecuredWorldResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class HelloSecuredWorldUseCaseTest {
    private UseCase<HelloSecuredWorldRequest, HelloSecuredWorldResponse> useCase;

    @BeforeEach
    void setUp() {
        useCase = new HelloSecuredWorld();
    }

    @Test
    void shouldSayHello() {
        //GIVEN
        final HelloSecuredWorldRequest given = HelloSecuredWorldRequest.builder().userName("Test").build();
        //WHEN
        final HelloSecuredWorldResponse response = useCase.execute(given);
        //THEN
        then(response).isEqualTo(HelloSecuredWorldResponse.builder().result("Hello Test!").build());
    }
}
