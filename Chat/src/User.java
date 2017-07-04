import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by dmy on 2017/6/13.
 */
public class User {


    private Socket socket;
    private String name;

    BufferedReader bufferedReader;
    public User(Socket socket,String name){
        this.socket=socket;
        this.name=name;
    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String action(){

        String temp="";
        try {


            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            temp = bufferedReader.readLine();

        }catch (Exception e){
            e.printStackTrace();



        }

        return temp;

    }


}


