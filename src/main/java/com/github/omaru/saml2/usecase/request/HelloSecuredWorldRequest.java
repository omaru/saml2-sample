package com.github.omaru.saml2.usecase.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class HelloSecuredWorldRequest {
    private String userName;
}
