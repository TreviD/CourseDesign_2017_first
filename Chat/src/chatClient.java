import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmy on 2017/6/13.
 */
public class chatClient {



    public static List<String> nameList = new ArrayList<String>();

    public static Socket socket = null;
    BufferedReader br = null;
    PrintWriter pw = null;
    
    public static String name="";

    public static int state=1;              //状态 为1时，表示有新用户上线 需要刷新用户列表






    public static List<ChatFrame> frameList = new ArrayList<ChatFrame>();

    public static int publicFrame=0;
    public static PublicChatFrame publicChatFrame;



    public void initConnect(String name){
        System.out.println("开始初始化连接");

        try {

            socket = new Socket("127.0.0.1", 8888);
            System.out.println(socket);

            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            pw.println(name);
            pw.flush();
//            System.out.print("123");
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            while(true) {
                String a=br.readLine();

                if(!a.equals("end")){
                    nameList.add(a);
                    System.out.println(a + "   get");
                }else{
                    break;
                }
//                System.out.print("321");
            }

            System.out.println("初始化连接完成");

            Thread thread =new Thread(){

                @Override
                public void run(){
                    while (true){
                        try {
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                            String text = bufferedReader.readLine();
                            System.out.println("thread===="+text);
                            doWhat(text);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }



                }



            };
            thread.start();


        }catch (Exception e){

            e.printStackTrace();
            System.out.println("初始化连接失败" );


        }



    }






    public static void privateChat(String name ,String text) throws IOException {

        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        String temp = "1"+name+"#"+text;
        printWriter.println(temp);
        printWriter.flush();


    }


    public static void publicChat(String text,String name){

        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

            String temp = "2"+name+"#"+text;
            printWriter.println(temp);
            printWriter.flush();



        } catch (IOException e) {
            e.printStackTrace();
        }


    }






    public void doWhat(String text){

        System.out.println(text);


        String dowhat = text.substring(0,1);
        String temp = text.substring(1,text.length());

        switch (Integer.parseInt(dowhat)){

            case 1:                                         //私聊   显示





                String name = temp.substring(0,temp.indexOf("#"));
                System.out.println("第一个#出现在："+temp.indexOf("#"));
                String content = temp.substring(temp.indexOf("#")+1,temp.length());

                System.out.println(name+"  ========"+content);

                int i=0;

                for( i=0;i<frameList.size();i++) {

                    if(name.equals(frameList.get(i).getName())){
                        break;
                    }


                }
                if(i==frameList.size()) {
                    ChatFrame chatFrame = new ChatFrame();
                    chatFrame.showMe(name);
                    frameList.add(chatFrame);
                    chatFrame.jTextArea.append(name + ":" + content + "\n");
                }else {
                    frameList.get(i).jTextArea.append(name + ":" + content + "\n");
                }

                break;



            case 2:                                     //群聊 显示

                String fromname = temp.substring(0,temp.indexOf("#"));
                String publicContent = temp.substring(temp.indexOf("#")+1,temp.length());

                if(publicFrame==0){
                    publicChatFrame = new PublicChatFrame();
                    publicChatFrame.showMe(mainFrame.name);
                    publicFrame=1;
                }
                publicChatFrame.jTextArea.append(fromname+":"+publicContent+"\n");



                System.out.println("收到群发消息：" + temp);


                break;



            case 3:

                nameList.add(temp);
                state=1;

                break;



                default:break;
        }









    }









/*
    public void getChat(){



        try {


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String text = bufferedReader.readLine();


        System.out.println("text");
        
        if(!text.equals("")){
            
            String doWhat = text.substring(0,1);
            
            String fromname = text.substring(1,text.indexOf("#"));
            
            switch (Integer.parseInt(doWhat)){
                case 1:


                    viewPrivateChat(fromname,text);             //显示私聊信息
                    
                    break;
                
                case 2:


//                    viewPublicChat(fromname,text);              //显示群聊信息

                    break;

                case 3:


                default:break;
                
            }
            
            
            
            
        }




        }catch (Exception e){



        }



    }
*/
    private void viewPublicChat(String fromname, String text) {



        PublicChatFrame.jTextArea.append(fromname+":"+text);


    }

    private void viewPrivateChat(String fromname, String text) {

//        ChatFrame.jTextArea.append(fromname+":"+text);

    }


}
