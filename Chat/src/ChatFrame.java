import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by dmy on 2017/6/13.
 */
public class ChatFrame extends JFrame {


    JScrollPane jScrollPane = new JScrollPane();
    public  JTextArea jTextArea = new JTextArea();

    JTextField jTextField = new JTextField();

    JButton jButton = new JButton("发送");


    String name;
//    chatClient client=new chatClient();


    public void showMe(String name) {


        this.name=name;


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

        this.setTitle("聊天:"+name);
        this.add(jButton);





        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    chatClient.privateChat(name,jTextField.getText());
                    System.out.println("发送成功");
                    jTextArea.append("我："+jTextField.getText()+"\n");
                    jTextField.setText("");
                } catch (IOException e1) {
                    e1.printStackTrace();
                    System.out.println("发送失败");
                }
            }
        });



        this.setVisible(true);
    }



    public String getName(){
        return name;
    }



}
