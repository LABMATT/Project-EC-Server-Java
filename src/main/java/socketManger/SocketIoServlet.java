package socketManger;

import io.socket.emitter.Emitter;
import io.socket.engineio.server.EngineIoServer;
import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoSocket;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;


@WebServlet("/socket.io/*")
public class SocketIoServlet extends HttpServlet {

    private final EngineIoServer mEngineIoServer = new EngineIoServer();
    private final SocketIoServer mSocketIoServer = new SocketIoServer(mEngineIoServer);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        mEngineIoServer.handleRequest(request, response);
    }

    this.on("connection", new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            SocketIoSocket socket = (SocketIoSocket) args[0];
            // Do something with socket
        }
    });
}