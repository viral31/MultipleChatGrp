import javax.imageio.IIOException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class grpchat_Server {
    private ServerSocket serverSocket;

    public grpchat_Server(ServerSocket serverSocket) {
        this.serverSocket= serverSocket;
    }
    public void startServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("new client is connected");
                ClientHandler clienthandler = new ClientHandler(socket);
                Thread thread = new Thread(clienthandler);
                thread.start();
            }
        }catch(Exception e){
            e.printStackTrace();

        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        grpchat_Server server = new grpchat_Server(serverSocket );
        server.startServer();
    }
}
