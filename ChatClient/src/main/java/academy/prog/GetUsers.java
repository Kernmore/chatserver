package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetUsers {

    public static void printUsers() throws IOException {
        Gson gson = new GsonBuilder().create();
        URL url = new URL(Utils.getURL() + "/users");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        InputStream is = http.getInputStream();

        try {
            byte[] buf = responseBodyToArray(is);
            String strBuf = new String(buf, StandardCharsets.UTF_8);
            User[] users = gson.fromJson(strBuf, User[].class);
            if (users != null) {
                for (User u : users) {
                    System.out.println(u);
                }
            }

        } finally {
            is.close();
        }
    }

    private static byte[] responseBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();

    }
}
