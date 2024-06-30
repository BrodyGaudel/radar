package org.mounanga.registrationservice.commands.util;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean
    public CommandBus commandBus(AggregateCommandDispatchInterceptor interceptor) {
        CommandBus commandBus = SimpleCommandBus.builder().build();
        commandBus.registerDispatchInterceptor(interceptor);
        return commandBus;
    }
}
