package com.github.omaru.saml2;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;

@TestConfiguration
public class SecurityConfigTest {
    @MockBean
    RelyingPartyRegistrationRepository relyingPartyRegistrations;
}
