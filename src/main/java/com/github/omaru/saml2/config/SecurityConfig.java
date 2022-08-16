package com.github.omaru.saml2.config;

import lombok.RequiredArgsConstructor;
import org.opensaml.security.x509.X509Support;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor
public class SecurityConfig {
    private final AppProperties appProperties;

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
        final AppProperties.Saml2Properties saml2Properties = appProperties.getSaml2Properties();
        final ClassLoader classLoader = getClass().getClassLoader();
        final File verificationKey = new File(classLoader.getResource(saml2Properties.getCertificatePath()).getFile());
        final X509Certificate certificate = X509Support.decodeCertificate(verificationKey);
        final Saml2X509Credential credential = Saml2X509Credential.verification(certificate);
        final RelyingPartyRegistration registration = RelyingPartyRegistration
                .withRegistrationId(saml2Properties.getRegistrationId())
                .assertingPartyDetails(party -> party
                        .entityId(saml2Properties.getEntityId())
                        .singleSignOnServiceLocation(saml2Properties.getSsoServiceLocationURI())
                        .wantAuthnRequestsSigned(saml2Properties.getSignedAuthRequest())
                        .verificationX509Credentials(c -> c.add(credential))
                ).build();
        return new InMemoryRelyingPartyRegistrationRepository(registration);
    }
}
