package com.github.omaru.saml2.usecase;

public interface UseCase<REQUEST, RESPONSE> {
    RESPONSE execute(REQUEST request);
}
