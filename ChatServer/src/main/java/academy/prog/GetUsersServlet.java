package academy.prog;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet //(name = "GetUsersServlet", value = "/users")
public class GetUsersServlet extends HttpServlet {
    private UsersList usersList = UsersList.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String json = usersList.toJSON();
        if (json != null) {
            OutputStream os = response.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }


}
