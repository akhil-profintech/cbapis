package in.pft.apis.creditbazaar.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig
{
    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }
}
