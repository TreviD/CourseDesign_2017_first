import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmy on 2017/6/13.
 */
public class LoginFrame extends JFrame {













    public void showMe(){




        JLabel jLabel = new JLabel("请输入用户名(英文)：");

        JTextField name = new JTextField();
        JButton OK = new JButton("确认");


        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatClient.name=name.getText();

                dispose();
//                ChatFrame chatFrame = new ChatFrame();
//                chatFrame.setVisible(true);

                Thread thread = new Thread() {



                    @Override
                    public void run() {
                        mainFrame mainFrame = new mainFrame(name.getText());
                        mainFrame.setName(name.getText());
                        while(true)   {
                            if(chatClient.state==1){

                            mainFrame.showMe();
                            chatClient.state=0;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                };

                thread.start();
            }
        });

        jLabel.setBounds(200,100,150,50);
        name.setBounds(200,160,100,30);
        OK.setBounds(240,250,50,30);




        this.setSize(500,400);
        this.setLayout(null);
        this.setLocation(300,300);
        this.setTitle("聊天小程序v0.1_alpha");
        this.add(jLabel);
        this.add(name);
        this.add(OK);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




    }


    public static void main(String[] args){

        LoginFrame loginFrame = new LoginFrame();

        loginFrame.showMe();




    }












}
