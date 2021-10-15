package ru.cherry.springhomework.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.cherry.springhomework.service.TransformService;

@Configuration
@RequiredArgsConstructor
public class IntegrationConfig {
    private final TransformService transformService;

    @Bean
    public QueueChannel frogChannel() {
        return MessageChannels.queue(5).get();
    }

    @Bean
    public PublishSubscribeChannel princessChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER )
    public PollerMetadata poller () {
        return Pollers.fixedRate(200)
                .maxMessagesPerPoll(2).get() ;
    }

    @Bean
    public IntegrationFlow integrationFlow() {
        return IntegrationFlows.from("frogChannel")
                .handle(transformService, "transform")
                .channel("princessChannel")
                .get();
    }
}
