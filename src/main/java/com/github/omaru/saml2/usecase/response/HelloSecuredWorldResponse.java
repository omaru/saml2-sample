package com.github.omaru.saml2.usecase.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class HelloSecuredWorldResponse {
    private String result;
}
