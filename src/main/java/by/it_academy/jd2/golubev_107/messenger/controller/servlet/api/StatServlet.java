package by.it_academy.jd2.golubev_107.messenger.controller.servlet.api;

import by.it_academy.jd2.golubev_107.messenger.service.IStatService;
import by.it_academy.jd2.golubev_107.messenger.service.dto.statistics.StatResultDto;
import by.it_academy.jd2.golubev_107.messenger.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/admin/statistics")
public class StatServlet extends HttpServlet {

    private final IStatService statService = ServiceFactory.getInstance().getStatService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatResultDto results = statService.getResults();
        req.setAttribute("statResult", results);
        req.getRequestDispatcher("/jsp/statistics.jsp").forward(req, resp);
    }
}
