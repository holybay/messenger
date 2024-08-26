package controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;
import service.dto.UserLoginInDto;
import service.dto.UserLoginOutDto;
import service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {

    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_PASSWORD = "password";
    private final UserService service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/user_login_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try {
            UserLoginInDto userIn = toUserLoginInDto(req);
            UserLoginOutDto userOut = service.login(userIn);
            HttpSession session = req.getSession();
            session.setAttribute("user", userOut);
        } catch (IllegalArgumentException e) {
            writer.print(e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/api/login");
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
