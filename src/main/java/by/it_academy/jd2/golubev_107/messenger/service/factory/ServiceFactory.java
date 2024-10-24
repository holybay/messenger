package by.it_academy.jd2.golubev_107.messenger.service.factory;

import by.it_academy.jd2.golubev_107.messenger.service.IMessageService;
import by.it_academy.jd2.golubev_107.messenger.service.IUserService;
import by.it_academy.jd2.golubev_107.messenger.service.impl.MessageService;
import by.it_academy.jd2.golubev_107.messenger.service.impl.UserService;
import by.it_academy.jd2.golubev_107.messenger.storage.factory.StorageFactory;

public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory(StorageFactory.getInstance());
    private final IUserService userService;
    private final IMessageService messageService;

    public ServiceFactory(StorageFactory storageFactory) {
        this.userService = new UserService(storageFactory.getUserStorage());
        this.messageService = new MessageService(storageFactory.getMessageStorage(), userService);
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public IUserService getUserService() {
        return userService;
    }

    public IMessageService getMessageService() {
        return messageService;
    }
}

