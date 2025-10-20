package tankgame;

import javax.swing.*;

//提供JFrame框架
public class Tankgame extends JFrame {
    MyPanel mp = null;
    public static void main(String[] args) {
        new Tankgame();
    }

    public Tankgame() {
        mp = new MyPanel();
        this.add(mp);//引入面板
        this.setSize(1000, 750);//窗口大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
