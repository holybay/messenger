package by.it_academy.jd2.golubev_107.messenger.controller.servlet.api;

import by.it_academy.jd2.golubev_107.messenger.service.IMessageService;
import by.it_academy.jd2.golubev_107.messenger.service.dto.message.MessageCreateDto;
import by.it_academy.jd2.golubev_107.messenger.service.dto.message.MessageOutDto;
import by.it_academy.jd2.golubev_107.messenger.service.dto.user.UserOutDto;
import by.it_academy.jd2.golubev_107.messenger.service.factory.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@WebServlet("/api/message")
public class MessageServlet extends HttpServlet {

    public static final String SESSION_USER = "user";
    public static final String PARAM_TO = "to";
    public static final String PARAM_TEXT = "text";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");
    private final IMessageService messageService = ServiceFactory.getInstance().getMessageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserOutDto user = getUserFromSession(req);
            List<MessageOutDto> allMessagesForUser = messageService.getAll(user);
            req.setAttribute("messages", allMessagesForUser);
            req.setAttribute("formatter", formatter);
            req.getRequestDispatcher("/jsp/income_messages.jsp").forward(req, resp);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/ui/user/message").forward(req, resp);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/jsp/error.jsp");
        } catch (RuntimeException e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/jsp/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserOutDto user = getUserFromSession(req);
            MessageCreateDto newMessage = toMessageDto(req, user);
            messageService.create(newMessage);
            req.getRequestDispatcher("/jsp/message_sent_success.jsp").forward(req, resp);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/jsp/message_create_form.jsp").forward(req, resp);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/jsp/error.jsp");
        } catch (RuntimeException e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/jsp/error.jsp");
        }
    }

    private UserOutDto getUserFromSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute(SESSION_USER) instanceof UserOutDto user) {
            return user;
        }
        session.removeAttribute(SESSION_USER);
        throw new IllegalArgumentException("Incorrect user attribute!");
    }

    private MessageCreateDto toMessageDto(HttpServletRequest req, UserOutDto user) {
        MessageCreateDto newMessage = new MessageCreateDto();
        newMessage.setFrom(user);
        newMessage.setTo(req.getParameter(PARAM_TO));
        newMessage.setText(req.getParameter(PARAM_TEXT));
        return newMessage;
    }
}
