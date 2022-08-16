package com.github.omaru.saml2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app")
@Data
public class AppProperties {
    private Saml2Properties saml2Properties;

    @Data
    public static class Saml2Properties {
        private String registrationId;
        private String entityId;
        private String ssoServiceLocationURI;
        private String certificatePath;
        private Boolean signedAuthRequest;
    }
}
