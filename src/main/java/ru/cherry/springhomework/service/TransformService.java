package ru.cherry.springhomework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cherry.springhomework.domain.Frog;
import ru.cherry.springhomework.domain.Princess;

@Service
@RequiredArgsConstructor
public class TransformService {
    private final MessageService messageService;

    public Princess transform(Frog frog) throws InterruptedException {
        messageService.sendMessage("Преобразуем лягушку " + frog.getName() + " в царевну.");
        Thread.sleep(1000);

        Princess princess = new Princess(frog.getName().toUpperCase());

        messageService.sendMessage("Царевна " + princess.getName() + " готова!");
        return princess;
    }
}
