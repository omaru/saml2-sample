package com.github.omaru.saml2.config;

import org.opensaml.security.x509.X509Support;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.saml2.core.Saml2X509Credential;
import org.springframework.security.saml2.provider.service.registration.InMemoryRelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistration;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import java.io.File;
import java.security.cert.X509Certificate;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain httpSecurityConfig(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize ->
                        authorize.antMatchers("/")
                                .permitAll()
                                .antMatchers("/public**")
                                .permitAll()
                                .anyRequest().authenticated()
                ).saml2Login();
        return http.build();
    }

    @Bean
    protected RelyingPartyRegistrationRepository relyingPartyRegistrations() throws Exception {
        final ClassLoader classLoader = getClass().getClassLoader();
        final File verificationKey = new File(classLoader.getResource("saml2/okta.crt").getFile());
        final X509Certificate certificate = X509Support.decodeCertificate(verificationKey);
        final Saml2X509Credential credential = Saml2X509Credential.verification(certificate);
        final RelyingPartyRegistration registration = RelyingPartyRegistration
                .withRegistrationId("okta-saml")
                .assertingPartyDetails(party -> party
                        .entityId("http://www.okta.com/exk6sni93NCyDl9VP5d6")
                        .singleSignOnServiceLocation("https://dev-11017565.okta.com/app/dev-11017565_appsaml_1/exk6sni93NCyDl9VP5d6/sso/saml")
                        .wantAuthnRequestsSigned(false)
                        .verificationX509Credentials(c -> c.add(credential))
                ).build();
        return new InMemoryRelyingPartyRegistrationRepository(registration);
    }
}
