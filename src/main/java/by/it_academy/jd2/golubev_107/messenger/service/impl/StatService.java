package by.it_academy.jd2.golubev_107.messenger.service.impl;

import by.it_academy.jd2.golubev_107.messenger.service.IMessageService;
import by.it_academy.jd2.golubev_107.messenger.service.IStatService;
import by.it_academy.jd2.golubev_107.messenger.service.IUserService;
import by.it_academy.jd2.golubev_107.messenger.service.dto.statistics.StatResultDto;
import by.it_academy.jd2.golubev_107.messenger.storage.IStatStorage;
import by.it_academy.jd2.golubev_107.messenger.storage.entity.StatCounter;

public class StatService implements IStatService {

    private static final String ACTIVE_USERS_COUNTER = "active_users_count";
    private final IMessageService messageService;
    private final IUserService userService;
    private final IStatStorage statStorage;

    public StatService(IMessageService messageService, IUserService userService, IStatStorage statStorage) {
        this.messageService = messageService;
        this.userService = userService;
        this.statStorage = statStorage;
    }

    @Override
    public void incrementActiveUser() {
        StatCounter activeUserCounter = statStorage.readByName(ACTIVE_USERS_COUNTER);
        activeUserCounter.setValue(activeUserCounter.getValue() + 1);
        statStorage.update(activeUserCounter);
    }

    @Override
    public void decrementActiveUser() {
        StatCounter activeUserCounter = statStorage.readByName(ACTIVE_USERS_COUNTER);
        activeUserCounter.setValue(activeUserCounter.getValue() - 1);
        statStorage.update(activeUserCounter);
    }

    @Override
    public StatResultDto getResults() {
        return StatResultDto.builder()
                            .setActiveUsers(statStorage.readByName(ACTIVE_USERS_COUNTER).getValue())
                            .setTotalUsers(userService.countAll())
                            .setTotalMessagesSent(messageService.countAll())
                            .build();
    }
}
