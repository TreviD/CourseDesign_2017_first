import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmy on 2017/6/13.
 */




public class chatServer {

    ServerSocket serverSocket = null;
    Socket socket = null;
    BufferedReader bufferedReader = null;
    PrintWriter pw = null;


    List<User> userList = new ArrayList<User>();



    public void connect(){

        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("服务端启动：" + serverSocket);
        while (true) {


            try {
//                ServerSocket serverSocket = new ServerSocket(8888);
//                System.out.println("服务端启动：" + serverSocket);

                socket = serverSocket.accept();
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String name = bufferedReader.readLine();
                User user = new User(socket, name);
                int num=userList.size();
                System.out.println("num is "+num+" ----------");
                userList.add(user);


                //发送所有用户名
                pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                if(userList.size()>0) {
                    for (int i = 0; i < userList.size() - 1; i++) {
                        pw.println(userList.get(i).getName());
                        System.out.println(userList.get(i).getName());
                        pw.flush();
                    }
                }





                Thread.sleep(1000);

                //向所有客户端 发送上线信息

                for(int i=0; i < userList.size()-1;i++) {

                    PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(userList.get(i).getSocket().getOutputStream())),true);

                    String temp = "3"+name;

                    printWriter.println(temp);
                    printWriter.flush();



                }


                pw.println("end");
                pw.flush();
                System.out.println("初始化连接完成");










                Thread thread = new Thread(){
                  @Override
                    public void run(){
                      while (true){
                          try {
                              BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                              String temp = br.readLine();

                              doWhat(temp,num);



                          } catch (IOException e) {
                              e.printStackTrace();
                          }


                      }







                  }
                };


                thread.start();


            } catch (Exception e) {
                System.out.println("启动失败");
                e.printStackTrace();
            }


        }


    }






    //私聊   传入用户名和发送的内容
    public void privateChat(String name,String text,String fromname) throws IOException {

            int i=0;
            for(i=0;i<userList.size();i++){
                if(userList.get(i).getName().equals(name)){
                    System.out.println("userList num is "+i);
                    break;
                }
            }

            System.out.println("i is :==="+i);


            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(  userList.get(i).getSocket().getOutputStream()   )),true);
            String temp = "1"+fromname+"#"+text;
            pw.println(temp);
            pw.flush();



    }


    //群聊 传入要发送的内容

    public void publicChat(String text,String fromname) throws IOException {


        for(int i=0;i<userList.size();i++){
            if(!userList.get(i).getName().equals(fromname)){                //来源用户 不匹配的转发出去
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(userList.get(i).getSocket().getOutputStream())),true);

            String temp = "2"+fromname+"#"+text;

            printWriter.println(temp);
            printWriter.flush();
            }
        }




    }



    public void action(){

        Thread conn = new Thread(){             //连接线程

            @Override
            public void run(){

                connect();

            }
        };
        conn.start();
        System.out.println("连接线程启动成功");


        Thread get = new Thread(){

            @Override
            public void run(){


                //                          getMsg();
//                          System.out.println("接收成功");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        };

        get.start();
        System.out.println("接收线程启动成功");

    }








/*--------------------------------------   getMsg xxxxxxxxx      失败   --------------------------------


    //得到客户端发来的消息 并处理
    public void getMsg() throws IOException {

        List<Thread> threadList = new ArrayList<Thread>();

        for (int i = 0; i < userList.size(); i++) {

            int finalI = i;

            Thread t = new Thread() {

                @Override
                public void run() {
                    BufferedReader br = null;
                    try {
                        System.out.println("getMsg--------------");

                        br = new BufferedReader(new InputStreamReader(userList.get(finalI).getSocket().getInputStream()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String a = null;
                    try {
                        a = br.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    System.out.println("getMsg--------------"+a);
                    if (!a.equals(""))

                    {
                        System.out.println(a);
                        String doWhat = a.substring(0, 1);                   //得到操作代码 1 私聊 2群聊
                        String text = a.substring(1, a.length());

                        switch (Integer.parseInt(doWhat)) {
                            case 1:                                     //操作代码 1 私聊
                                String name = text.substring(0, text.indexOf("#"));
                                String content = text.substring(text.indexOf("#") + 1, text.length());
                                try {
                                    privateChat(name, content, userList.get(finalI).getName());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;

                            case 2:                                         //得到操作代码 2 群聊

                                String fromname = text.substring(0, text.indexOf("#"));
                                String content2 = text.substring(text.indexOf("#") + 1, text.length());
                                try {
                                    publicChat(content2, fromname);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                                break;

                            case 3:                                         //用户上线刷新


                                break;
                        }

                    }


                }
            };

            threadList.add(t);
            t.start();
        }



    }



------------------------------------------------------*/



public void doWhat(String text,int num){



    System.out.println("doWhat    "+text);
    String doWhat=text.substring(0,1);
    String a = text.substring(1, text.length());

    switch (Integer.parseInt(doWhat)) {
        case 1:                                     //操作代码 1 私聊
            String name = a.substring(0, a.indexOf("#"));
            String content = a.substring(a.indexOf("#") + 1, a.length());
            try {
                System.out.println("name is :====="+userList.get(num).getName());
                System.out.println("name is : =======-----"+name);

                privateChat(name, content, userList.get(num).getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;

        case 2:                                         //得到操作代码 2 群聊

            String fromname = a.substring(0, a.indexOf("#"));
            String content2 = a.substring(a.indexOf("#") + 1, a.length());
            try {
                publicChat(content2, fromname);
            } catch (IOException e) {
                e.printStackTrace();
            }


            break;

        case 3:                                         //用户上线刷新


            break;
    }








}






    public static void main(String[] args){

        chatServer server = new chatServer();
        server.action();



    }









}
