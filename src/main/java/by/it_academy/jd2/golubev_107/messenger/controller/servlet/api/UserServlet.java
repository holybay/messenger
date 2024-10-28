package by.it_academy.jd2.golubev_107.messenger.controller.servlet.api;

import by.it_academy.jd2.golubev_107.messenger.service.IUserService;
import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserCreateInDto;
import by.it_academy.jd2.golubev_107.messenger.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/api/user")
public class UserServlet extends HttpServlet {
    private static final String PARAM_FULL_NAME = "fullName";
    private static final String PARAM_DOB = "dateOfBirth";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private final IUserService service = ServiceFactory.getInstance().getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try {
            UserCreateInDto inDto = toInputDto(req);
            if (service.create(inDto)) {
                writer.println("User CREATED!");
            } else {
                writer.println("Something went wrong...");
            }
        } catch (IllegalArgumentException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            writer.println(e.getMessage());
        }
    }

    private UserCreateInDto toInputDto(HttpServletRequest req) {
        UserCreateInDto dto = new UserCreateInDto();
        dto.setFullName(req.getParameter(PARAM_FULL_NAME));
        dto.setDateOfBirth(dateParse(req));
        dto.setLogin(req.getParameter(PARAM_LOGIN));
        dto.setPassword(req.getParameter(PARAM_PASSWORD));
        return dto;
    }

    private LocalDate dateParse(HttpServletRequest req) {
        String dobRaw = req.getParameter(PARAM_DOB);
        if (dobRaw == null) {
            throw new IllegalArgumentException("Wrong date format. Please check the provided input!");
        }
        return LocalDate.parse(dobRaw);
    }

}
