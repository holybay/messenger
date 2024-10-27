package by.it_academy.jd2.golubev_107.messenger.service;

import by.it_academy.jd2.golubev_107.messenger.service.dto.statistics.StatResultDto;

public interface IStatService {

    void incrementActiveUser();

    void decrementActiveUser();

    StatResultDto getResults();
}
