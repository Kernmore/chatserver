package academy.prog;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet //(name = "PrivateMessagesServlet", value = "/PrivateMessagesServlet")
public class PrivateMessagesServlet extends HttpServlet {

    private MessageList msgList = MessageList.getInstance();
    private UsersList usersList = UsersList.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromStr = request.getParameter("from");
        String login = request.getParameter("login");

        if (!usersList.checkPresence(login)) {
            User u = new User(login);
            usersList.add(u);
        }

        int from = 0;
        try {
            from = Integer.parseInt(fromStr);
            if (from < 0) from = 0;
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        response.setContentType("application/json");

        String json = msgList.toJSON(from);

        if (json != null) {
            OutputStream os = response.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);

            //PrintWriter pw = resp.getWriter();
            //pw.print(json);
        }
    }

}



