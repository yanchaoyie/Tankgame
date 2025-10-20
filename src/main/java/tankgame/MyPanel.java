package tankgame;

import javax.swing.*;
import java.awt.*;

//创建绘图区，负责绘制背景和坦克
public class MyPanel extends JPanel {
    Player palyer = null;
    public MyPanel() {
        palyer = new Player(1000,1000);//初始化玩家坦克
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//绘制背景
        //将坦克的绘制封装成一个方法方便调用，这样也可以很方便的绘制多个坦克
        drawTank(palyer.getX(),palyer.getY(),g,0,0);
        //drawTank(palyer.getX() + 60,palyer.getY(),g,0,1);有了方法的封装，减少代码的重复
    }

    /**
     *
     * @param x 坦克的x坐标
     * @param y 坦克的y坐标
     * @param g 画笔
     * @param direct 坦克方向
     * @param type 坦克类型
     */
    public void drawTank(int x,int y,Graphics g,int direct,int type){
        switch(type){
            case 0://玩家坦克
                g.setColor(Color.cyan);
                break;
            case 1://敌人坦克
                g.setColor(Color.yellow);
                break;
        }
        switch(direct){
            case 0:
                g.fill3DRect(x,y,10,60,false);//此方法让坦克更加真实，画坦克的履带
                g.fill3DRect(x + 10,y + 10,20,40,false);//画坦克的身体
                g.fill3DRect(x + 30,y,10,60,false);//画坦克的履带
                g.fillOval(x + 10,y + 20,20,20);//画坦克的圆形盖子
                g.fill3DRect(x + 20,y,1,30, false);//画坦克的炮管
                break;

        }
    }
}
