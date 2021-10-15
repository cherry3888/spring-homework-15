package ru.cherry.springhomework.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.cherry.springhomework.domain.Frog;
import ru.cherry.springhomework.domain.Princess;
import ru.cherry.springhomework.integration.FrogPrincessGateway;
import ru.cherry.springhomework.service.MessageService;

import java.util.Random;

@ShellComponent
public class ShellUI {
    private final MessageService messageService;
    private static String[] names = {"Маша", "Даша", "Шуша", "Глаша", "Таня","Маня"};
    private final FrogPrincessGateway frogPrincessGateway;

    public ShellUI(MessageService messageService, FrogPrincessGateway frogPrincessGateway) {
        this.messageService = messageService;
        this.frogPrincessGateway = frogPrincessGateway;
    }

    @ShellMethod(value = "Start", key = {"s", "start"})
    public void startApp() throws InterruptedException {
        messageService.sendMessage("Start");

        while (true) {
            Thread.sleep(2000);
            Thread thread = new Thread(() -> {
                Frog frog = new Frog(names[new Random().nextInt(5)] );
                messageService.sendMessage("Родилась лягушка " + frog.getName());
                Princess princess = frogPrincessGateway.process(frog);
            });
            thread.start();
        }

    }

}
