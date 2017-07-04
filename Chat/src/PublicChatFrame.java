import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by dmy on 2017/6/13.
 */
public class PublicChatFrame extends JFrame {


    JScrollPane jScrollPane = new JScrollPane();
    public static JTextArea jTextArea = new JTextArea();

    JTextField jTextField = new JTextField();

    JButton jButton = new JButton("发送");

//    chatClient client=new chatClient();


    public void showMe(String name) {





        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500,400);
        this.setLocation(300,300);

        jTextArea.setBounds(0,0,400,300);
        jScrollPane.setBounds(0,0,400,300);
        jTextField.setBounds(0,310,300,50);
        jButton.setBounds(310,310,100,50);
        this.add(jScrollPane.add(jTextArea));
        this.add(jTextField);

        this.setTitle("群聊");
        this.add(jButton);





        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                chatClient.publicChat(jTextField.getText(),name);
                System.out.println("发送成功"+"发送自"+name);
                jTextArea.append("我："+jTextField.getText()+"\n");
                jTextField.setText("");
            }
        });



        this.setVisible(true);
    }





}
