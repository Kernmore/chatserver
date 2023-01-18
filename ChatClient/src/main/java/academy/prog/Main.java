package academy.prog;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter your login: ");
            String login = scanner.nextLine();

            Thread th = new Thread(new GetThread(login));
            th.setDaemon(true);
            th.start();

            System.out.println("Enter your message: ");
            while (true) {

                String text = scanner.nextLine();
                if (text.isEmpty()) break;

                // users
                // @test Hello
                String to = null;
                if (text.contains("@")) {
                    to = text.substring(text.indexOf("@") + 1, text.indexOf(" "));
                    text = text.substring(text.indexOf(" "));
                    System.out.println("private message sent");
                }

                if (text.startsWith("/")) {
                    String command = text.substring(text.indexOf("/") + 1);
                    checkCommand(command, login);
                    continue;
                }
                Message m = new Message(login, to, text);
                int res = m.send(Utils.getURL() + "/add");

                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occurred: " + res);
                    return;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    public static void checkCommand(String command, String login) throws IOException {
        if (command.equals("users")) {
            GetUsers.printUsers();
        }
        String[] commands;
        User u = new User(login);
        if (command.contains(" ")) {
            commands = command.split(" ");

            if (commands[0].equals("setstatus")) {
                if (commands[1].equals("offline")) {
                    u.setActive(false);
                }
                if (commands[1].equals("online")) {
                    u.setActive(true);
                }
                SetStatus status = new SetStatus(u);
                int res = status.send(Utils.getURL() + "/status");
                System.out.println("Status changed");
                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occurred: " + res);
                    return;
                }
            }
        }
    }

}
