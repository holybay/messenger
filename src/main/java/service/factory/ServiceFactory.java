package service.factory;

import service.IUserService;
import service.impl.UserService;
import storage.factory.StorageFactory;

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
