package by.it_academy.jd2.golubev_107.messenger.service.factory;

import by.it_academy.jd2.golubev_107.messenger.service.IUserService;
import by.it_academy.jd2.golubev_107.messenger.service.impl.UserService;
import by.it_academy.jd2.golubev_107.messenger.storage.factory.StorageFactory;

public class ServiceFactory {

    private static final ServiceFactory INSTANCE = new ServiceFactory(StorageFactory.getInstance());
    private final IUserService userService;

    public ServiceFactory(StorageFactory storageFactory) {
        this.userService = new UserService(storageFactory.getUserStorage());
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }

    public IUserService getUserService() {
        return userService;
    }
}
