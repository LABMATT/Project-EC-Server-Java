package MessageManger;

import DoludeManger.HostSession;
import DoludeManger.JoinSession;
import DoludeManger.RegisterSession;
import org.java_websocket.WebSocket;

public class Message implements Runnable {

    // The recived message on initinalation.
    private final String message;
    private final WebSocket connection;

    // When class is called then init this class for the message
    public Message(String message, WebSocket connection) {

        this.message = message;
        this.connection = connection;
    }

    // Reciveds message as new thread and exacutes it accordingly.
    @Override
    public void run() {

        // Makes sure there is a command sep.
        if (message.contains(":")) {

            final int prefixpos = message.indexOf(':');

            String prefix = message.substring(0, prefixpos);
            String sufix = message.substring(prefixpos + 1);

            try {
                prefix = new Sanitizer().sanitize(prefix, 20);
                sufix = new Sanitizer().sanitize(sufix, 20);

                switch (prefix) {
                    case "register" -> new RegisterSession();
                    case "host" -> new HostSession();
                    case "join" -> new JoinSession();
                    default -> connection.send(prefix + " invalid.");
                }

            } catch (Exception e) {
                connection.send(prefix + ": Input unsuitable for use <" + e.getMessage() + ">");
            }

            System.out.println("prefix: " + prefix);
            System.out.println("suffix: " + sufix);

            System.out.println("the thread ran");


            System.out.println("The message is: " + message);

        } else
        {
            connection.send("Input unsuitable for use <Missing Prefix>");
        }


    }
}
