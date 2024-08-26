package controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import service.dto.UserCreateInDto;
import service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/api/user")
public class UserServlet extends HttpServlet {
    private static final String ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "text/html; charset=utf-8";
    private static final String PARAM_FULL_NAME = "fullName";
    private static final String PARAM_DOB = "dateOfBirth";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private final UserService service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/user_create_form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setEncodingContentType(req, resp);
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

    private void setEncodingContentType(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding(ENCODING);
        resp.setContentType(CONTENT_TYPE);
    }

    private UserCreateInDto toInputDto(HttpServletRequest req) {
        UserCreateInDto dto = new UserCreateInDto();
        dto.setFullName(req.getParameter(PARAM_FULL_NAME));
        dto.setDateOfBirth(dateParse(req, PARAM_DOB));
        dto.setLogin(req.getParameter(PARAM_LOGIN));
        dto.setPassword(req.getParameter(PARAM_PASSWORD));
        return dto;
    }

    private LocalDate dateParse(HttpServletRequest req, String paramName) {
        String dobRaw = req.getParameter(paramName);
        if (dobRaw == null) {
            throw new IllegalArgumentException("Wrong date format. Please check the provided input!");
        }
        return LocalDate.parse(dobRaw);
    }

}
