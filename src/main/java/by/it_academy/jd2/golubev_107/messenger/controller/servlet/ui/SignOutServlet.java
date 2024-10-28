package by.it_academy.jd2.golubev_107.messenger.controller.servlet.ui;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ui/signOut")
public class SignOutServlet extends HttpServlet {

    private static final String SESSION_USER_ATTRIB = "user";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute(SESSION_USER_ATTRIB) != null) {
            session.removeAttribute(SESSION_USER_ATTRIB);
            resp.sendRedirect(req.getContextPath() + "/ui");
        }
    }
}
