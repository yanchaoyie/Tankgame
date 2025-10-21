package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//创建绘图区，负责绘制背景和坦克
//添加监听器
public class MyPanel extends JPanel implements KeyListener {
    Player palyer = null;
    Vector<Enemies> enemies = new Vector<>();//创建一个敌人坦克的集合使用Vector是因为线程安全
    int enemyNum = 3;

    //在构造器中创建坦克可以在类的对象实例化的时候一起生成坦克
    public MyPanel() {
        palyer = new Player(10,0);//初始化玩家坦克
        palyer.setSpeed(1);//设置坦克速度
        for(int i = 0; i < enemyNum; i++){
            Enemies enemy = new Enemies(50 * i,0);//可以对坦克进行初始化后放进集合
            enemy.setType(1);
            enemy.setDirect(2);
            enemies.add(enemy);
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//绘制背景
        //将坦克的绘制封装成一个方法方便调用，这样也可以很方便的绘制多个坦克
        drawTank(palyer.getX(),palyer.getY(),g,palyer.getDirect(),palyer.getType());
        //drawTank(palyer.getX() + 60,palyer.getY(),g,0,1);有了方法的封装，减少代码的重复
        for(int i = 0; i < enemies.size(); i++){
            Enemies enemy = enemies.get(i);
            drawTank(enemy.getX(),enemy.getY(),g,enemy.getDirect(),enemy.getType());
        }
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
        switch(direct){//0表示向上，1表示向右，2表示向下，3表示向左
            case 0:
                g.fill3DRect(x,y,10,60,false);//此方法让坦克更加真实，画坦克的履带
                g.fill3DRect(x + 10,y + 10,20,40,false);//画坦克的身体
                g.fill3DRect(x + 30,y,10,60,false);//画坦克的履带
                g.fillOval(x + 10,y + 20,20,20);//画坦克的圆形盖子
                g.fill3DRect(x + 20,y,1,30, false);//画坦克的炮管
                break;
            case 1:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x + 10,y + 10,40,20,false);
                g.fill3DRect(x,y + 30,60,10,false);
                g.fillOval(x + 20,y + 10,20,20);
                g.fill3DRect(x + 30,y + 20,30,1, false);
                break;
            case 2:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x + 10,y + 10,20,40,false);
                g.fill3DRect(x + 30,y,10,60,false);
                g.fillOval(x + 10,y + 20,20,20);
                g.fill3DRect(x + 20,y + 30,1,30, false);
                break;
            case 3:
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x + 10,y + 10,40,20,false);
                g.fill3DRect(x,y + 30,60,10,false);
                g.fillOval(x + 20,y + 10,20,20);
                g.fill3DRect(x,y + 20,30,1, false);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //控制坦克方向以及让坦克移动
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            palyer.setDirect(0);
            palyer.moveUp();
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            palyer.setDirect(1);
            palyer.moveRight();
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            palyer.setDirect(2);
            palyer.moveDown();
        }else if(e.getKeyCode() == KeyEvent.VK_A){
            palyer.setDirect(3);
            palyer.moveLeft();
        }
        this.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
