package tankgame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

//提供JFrame框架
public class Tankgame extends JFrame {
    MyPanel mp = null;
    public static void main(String[] args) {
        new Tankgame();
    }

    public Tankgame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("新游戏1，继续游戏2");
        String key = scanner.next();
        mp = new MyPanel(key);
        this.add(mp);//引入面板
        Thread thread = new Thread(mp);
        thread.start();
        this.setSize(1300, 750);//窗口大小
        this.addKeyListener(mp);//添加键盘监听
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //当检测到关闭窗口时把记录写入文件
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                MyReacord.writeRecord();
                System.exit(0);
            }
        });
    }
}
