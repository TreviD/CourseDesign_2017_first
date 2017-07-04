import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmy on 2017/6/13.
 */
public class mainFrame extends JFrame{



    chatClient client ;

    public  static String name="";


    public mainFrame(String name){
        this.name = name;
        client = new chatClient();
        System.out.println("");
        client.initConnect(name);

    }





    public void showMe(){
        JButton jbtn = new JButton("群聊");

        this.setTitle(name);


        System.out.print("\ndebug"+" showMe start");

        JButton [] jButton = new JButton[chatClient.nameList.size()];
        if(chatClient.nameList.size()>0) {
            for (int i = 0; i < chatClient.nameList.size(); i++) {
                String temp = new String(chatClient.nameList.get(i).getBytes());
                jButton[i] = new JButton(temp);
//                jButton[i].setText(temp);
                jButton[i].setBounds(50,i*30,100,30);
                this.add(jButton[i]);

                jButton[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ChatFrame chatFrame = new ChatFrame();
                        chatFrame.showMe(temp);
                        System.out.println("点击");
                        chatClient.frameList.add(chatFrame);
                    }
                });
            }
        }



        jbtn.setBounds(400,0,100,50);
        this.add(jbtn);

        jbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PublicChatFrame publicChatFrame = new PublicChatFrame();
                publicChatFrame.showMe(name);


            }
        });


        this.setLayout(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(500,400);
        this.setLocation(300,300);
        this.setVisible(true);

        System.out.println("完成");



    }


    public void setName(String name){
            this.name=name;
    }

    public String getName(){

        return name;
    }




}
