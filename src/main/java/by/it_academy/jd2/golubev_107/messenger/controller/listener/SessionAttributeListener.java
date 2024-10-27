package by.it_academy.jd2.golubev_107.messenger.controller.listener;

import by.it_academy.jd2.golubev_107.messenger.service.IStatService;
import by.it_academy.jd2.golubev_107.messenger.service.factory.ServiceFactory;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

    private static final String USER_ATTRIBUTE_NAME = "user";
    private final IStatService statService = ServiceFactory.getInstance().getStatService();

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        if (USER_ATTRIBUTE_NAME.equals(se.getName())) {
            statService.incrementActiveUser();
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        if (USER_ATTRIBUTE_NAME.equals(se.getName())) {
            statService.decrementActiveUser();
        }
    }
}
