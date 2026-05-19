package com.learn2code_v2.Shop_V2;

import com.learn2code_v2.Shop_V2.conf.WebSocketConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.AntPathMatcher;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;



import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class WebSocketTests {

    @InjectMocks
    private WebSocketConfig webSocketConfig;

    @Mock
    private MessageBrokerRegistry registry;

    @Test
    void shouldConfigureMessageBrokerCorrectly() {
        webSocketConfig.configureMessageBroker(registry);

        verify(registry).enableSimpleBroker("/topic");
        verify(registry).setApplicationDestinationPrefixes("/app");
        verify(registry).setCacheLimit(5);
        verify(registry).setPathMatcher(any(AntPathMatcher.class));

    }


}
