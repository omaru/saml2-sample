# saml2-sample
## SAML2 Spring Boot Example.
To run the app we only need to provide the okta.crt from the Identity Provider.
### SAML2 Settings
Modify `application.yml` to set the saml2 attributes needed
Identity Provider Issuer : `entityId`
Identity Provider Single Sign-On URL: `    ssoServiceLocationURI`
### Running 
 `./gradlew bootRun`        
        
