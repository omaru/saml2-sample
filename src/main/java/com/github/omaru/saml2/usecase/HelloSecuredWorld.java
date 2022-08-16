package com.github.omaru.saml2.usecase;

import com.github.omaru.saml2.usecase.request.HelloSecuredWorldRequest;
import com.github.omaru.saml2.usecase.response.HelloSecuredWorldResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloSecuredWorld implements UseCase<HelloSecuredWorldRequest, HelloSecuredWorldResponse> {
    @Override
    public HelloSecuredWorldResponse execute(final HelloSecuredWorldRequest request) {
        return HelloSecuredWorldResponse.builder()
                .result("Hello " + request.getUserName() + "!").build();
    }
}
