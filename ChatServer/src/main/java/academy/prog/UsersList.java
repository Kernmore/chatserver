package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashSet;

public class UsersList {
    private static final UsersList usersList = new UsersList();

    private final Gson gson;

    private HashSet<User> users = new HashSet<>();

    public static UsersList getInstance() {
        return usersList;
    }

    private UsersList() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(User u) {
        users.add(u);
    }

    public synchronized String toJSON() {
        return gson.toJson(users);
    }

    public boolean checkPresence(String s) {
        for (User u : users) {
            if (u.getLogin().equals(s))
                return true;
        }
        return false;
    }

    public void setStatus(User user) {
        for (User u : users) {
            if(u.getLogin().equals(user.getLogin())){
                boolean b = user.isActive();
                u.setActive(b);
            }
        }
    }


}
