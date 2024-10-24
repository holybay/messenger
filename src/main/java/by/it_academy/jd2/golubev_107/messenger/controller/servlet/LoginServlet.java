package by.it_academy.jd2.golubev_107.messenger.controller.servlet;

import by.it_academy.jd2.golubev_107.messenger.service.IUserService;
import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserLoginInDto;
import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserOutDto;
import by.it_academy.jd2.golubev_107.messenger.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {

    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_PASSWORD = "password";
    private final IUserService service = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/user_login_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try {
            UserLoginInDto userIn = toUserLoginInDto(req);
            UserOutDto userOut = service.login(userIn);
            HttpSession session = req.getSession();
            session.setAttribute("user", userOut);
        } catch (IllegalArgumentException e) {
            req.setAttribute("message", e.getMessage());
            doGet(req, resp);
        } catch (RuntimeException e) {
            printError(writer, e.getMessage());
        }
    }

    private void printError(PrintWriter writer, String errorMessages) {
        writer.println(errorMessages.replace("\n", "<br>"));
    }

    private UserLoginInDto toUserLoginInDto(HttpServletRequest req) {
        UserLoginInDto userIn = new UserLoginInDto();
        String login = req.getParameter(PARAM_LOGIN);
        userIn.setLogin(login);
        String password = req.getParameter(PARAM_PASSWORD);
        userIn.setPassword(password);
        return userIn;
    }


}
