package academy.prog;

public class User {
    private String login;
    private boolean isActive;

    public User(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("User: ").append(login).append(" | Status: ").append(getStatus()).toString();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getStatus(){
        if(isActive) return "Available";
        else return "Not Available";
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
