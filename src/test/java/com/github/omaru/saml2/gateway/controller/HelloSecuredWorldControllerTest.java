package com.github.omaru.saml2.gateway.controller;

import com.github.omaru.saml2.usecase.UseCase;
import com.github.omaru.saml2.usecase.request.HelloSecuredWorldRequest;
import com.github.omaru.saml2.usecase.response.HelloSecuredWorldResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HelloSecuredWorldController.class)
class HelloSecuredWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UseCase<HelloSecuredWorldRequest, HelloSecuredWorldResponse> useCase;

    @Test
    void shouldReturn200ForSecuredResource() throws Exception {
        //WHEN
        when(useCase.execute(HelloSecuredWorldRequest.builder().userName("logged in user").build()))
                .thenReturn(HelloSecuredWorldResponse.builder().result("Hello logged in user!").build());
        //THEN
        mockMvc.perform(get("/hello").with(user("logged in user")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                         "result": "Hello logged in user!"
                        }
                        """, true));
    }
}
