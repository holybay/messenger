package by.it_academy.jd2.golubev_107.messenger.controller.listener;

import by.it_academy.jd2.golubev_107.messenger.storage.factory.StorageFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.util.ArrayList;
import java.util.List;

public class AppListener implements ServletContextListener {

    private List<AutoCloseable> closeables;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        closeables = new ArrayList<>();
        closeables.add(StorageFactory.getInstance());
        sce.getServletContext().setRequestCharacterEncoding("UTF-8");
        sce.getServletContext().setResponseCharacterEncoding("text/html; charset=UTF-8");
        System.out.println("Context Initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        for (AutoCloseable closeable : closeables) {
            try {
                closeable.close();
            } catch (Exception e) {
                System.out.println("FAILED TO CLOSE" + e.getMessage());
            }
        }
        System.out.println("Context closed");
    }
}
