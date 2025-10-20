package tankgame;

import javax.swing.*;
import java.awt.*;

//创建绘图区，负责绘制背景和坦克
public class MyPanel extends JPanel {
    Player palyer = null;
    public MyPanel() {
        palyer = new Player(100,100);//初始化玩家坦克
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//绘制背景
    }
}
