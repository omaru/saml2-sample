# saml2-sample
## SAML2 Spring Boot Example.
To run the app we only need to provide the okta.crt from the Identity Provider.
### SAML2 Settings
1. Modify `application.yml` to set the saml2 attributes needed<br>
2. Identity Provider Issuer : `entityId`<br>
3. Identity Provider Single Sign-On URL: `    ssoServiceLocationURI`
### Running 
 `./gradlew bootRun`        
        
