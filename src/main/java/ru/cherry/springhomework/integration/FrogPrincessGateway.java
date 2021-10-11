package ru.cherry.springhomework.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.cherry.springhomework.domain.Frog;
import ru.cherry.springhomework.domain.Princess;

@MessagingGateway
public interface FrogPrincessGateway {

    @Gateway(requestChannel = "frogChannel", replyChannel = "princessChannel")
    Princess process(Frog frog);

}
