package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//创建绘图区，负责绘制背景和坦克
//添加监听器
public class MyPanel extends JPanel implements KeyListener, Runnable {
    Player palyer = null;
    Vector<Enemies> enemies = new Vector<>();//创建一个敌人坦克的集合使用Vector是因为线程安全
    int enemyNum = 3;
    Vector<Bomb> bombs = new Vector<>();//添加爆炸集合
    Image bomb1 = null;
    Image bomb2 = null;
    Image bomb3 = null;

    //在构造器中创建坦克可以在类的对象实例化的时候一起生成坦克
    public MyPanel() {
        palyer = new Player(10,0);//初始化玩家坦克
        palyer.setSpeed(10);//设置坦克速度

        //初始化敌人坦克
        for(int i = 0; i < enemyNum; i++){
            Enemies enemy = new Enemies(50 * (i + 10),50);//可以对坦克进行初始化后放进集合
            enemy.setType(1);
            //启动敌人线程
            Thread thread = new Thread(enemy);
            thread.start();
            enemies.add(enemy);
        }

        //加载爆炸图片
        bomb1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb1.png"));
        bomb2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb2.png"));
        bomb3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb3.png"));

    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//绘制背景
        //将坦克的绘制封装成一个方法方便调用，这样也可以很方便的绘制多个坦克
        //如果玩家坦克存活就会绘制
        if(palyer.isLive) {
            drawTank(palyer.getX(), palyer.getY(), g, palyer.getDirect(), palyer.getType());
        }
        //drawTank(palyer.getX() + 60,palyer.getY(),g,0,1);有了方法的封装，减少代码的重复

        //绘制敌人坦克
        for(int i = 0; i < enemies.size(); i++){
            Enemies enemy = enemies.get(i);
            if(enemy.enemyLive) {//敌人存活就会绘制坦克和绘制子弹
                //限制敌人坦克位置
                if (enemies.get(i).getX() >= 0 && enemy.getX() <= 1000 && enemy.getY() >= 0 && enemy.getY() <= 750) {
                    drawTank(enemy.getX(), enemy.getY(), g, enemy.getDirect(), enemy.getType());
                } else {//如果超出位置把坦克位置设为0，0
                    enemies.get(i).setX(0);
                    enemies.get(i).setY(0);
                }
                //绘制敌人子弹
                for (int j = 0; j < enemy.enemyShots.size(); j++) {
                    if (enemy.enemyShots.get(j).alive) {
                        g.drawOval(enemy.enemyShots.get(j).x, enemy.enemyShots.get(j).y, 2, 2);
                    } else {
                        enemy.enemyShots.remove(j);
                    }
                }
            }
        }

        //绘制子弹
        for(int i = 0; i < palyer.shots.size(); i++) {
            Shot shot = palyer.shots.get(i);
            if (palyer.shots.size() != 0 && palyer.shots.get(i).alive) {
                g.setColor(Color.white);
                g.drawOval(shot.x, shot.y, 2, 2);
            }else{
                palyer.shots.remove(shot);
            }
        }
        //爆炸发生加载爆炸图片
        for(int i = 0; i < bombs.size(); i++) {
            if (bombs.get(i).isLive) {
                Bomb bomb = bombs.get(i);
                if (bomb.life > 6) {
                    g.drawImage(bomb1, bomb.x, bomb.y, 60, 60, this);
                } else if (bomb.life > 3) {
                    g.drawImage(bomb2, bomb.x, bomb.y, 60, 60, this);
                } else if (bomb.life > 0) {
                    g.drawImage(bomb3, bomb.x, bomb.y, 60, 60, this);
                } else {
                    bomb.isLive = false;
                    bombs.remove(i);
                }
                bomb.life--;
            }
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
    //监听玩家操作
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            palyer.setDirect(0);
            if(palyer.getY() > 0) {
                palyer.moveUp();
            }
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            palyer.setDirect(1);
            if(palyer.getX() + 60 < 1000) {
                palyer.moveRight();
            }
        }else if(e.getKeyCode() == KeyEvent.VK_S){
            palyer.setDirect(2);
            if(palyer.getY() + 60 < 750) {
                palyer.moveDown();
            }
        }else if(e.getKeyCode() == KeyEvent.VK_A){
            palyer.setDirect(3);
            if(palyer.getX() > 0) {
                palyer.moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_J){
            palyer.shotEnemyTank();
        }
        this.repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    //引入线程机制，实现画布的自动刷新
    //把判断子弹是否打中坦克的方法放在run方法中就会随着画板的刷新来一起判断
    @Override
    public void run() {
        while ( true){
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断玩家子弹是否打中坦克
            if(palyer.shots.size() != 0 && enemies.size() != 0){
                for(int i = 0; i < enemies.size(); i++){
                    for(int j = 0; j < palyer.shots.size() ; j++){
                        try{
                        hitTank(palyer.shots.get(j),enemies.get(i));
                        }catch (Exception e){
                            while(true){
                                this.repaint();
                            }
                        }
                    }
                }
            }
            //判断玩家是否被击中
            hitPlayer();
            System.out.println("画板在刷新");
            this.repaint();
        }
    }

    //添加判断子弹攻击坦克的方法
    public void hitTank(Shot shot,Tank tank){
        switch (tank.getDirect()){
            case 0:
            case 1://子弹击中了坦克的范围
                if(shot.x > tank.getX() && shot.x < tank.getX() + 40
                && shot.y > tank.getY() && shot.y < tank.getY() + 60){
                    if(tank.getType() == 1){//如果坦克是敌人坦克就把敌人坦克的live设为false
                        Enemies enemy= (Enemies)tank;
                        enemy.enemyLive = false;
                        shot.alive = false;
                        //如果被打中就增加一个爆炸对象
                        bombs.add(new Bomb(enemy.getX(),enemy.getY()));
                        //如果被打中就删除打中的坦克
                        enemies.remove(enemy);
                    }else{//如果是玩家坦克就会判断玩家坦克死亡
                        palyer.isLive = false;
                        shot.alive = false;
                        bombs.add(new Bomb(palyer.getX(),palyer.getY()));
                    }
                }
                break;
            case 2:
            case 3:
                if(shot.x > tank.getX() && shot.x < tank.getX() + 60
                && shot.y > tank.getY() && shot.y < tank.getY() + 40){
                    if(tank.getType() == 1){
                        Enemies enemy= (Enemies)tank;
                        enemy.enemyLive = false;
                        shot.alive = false;
                        bombs.add(new Bomb(enemy.getX(),enemy.getY()));
                        enemies.remove(enemy);
                    }else{
                        palyer.isLive = false;
                        shot.alive = false;
                        bombs.add(new Bomb(palyer.getX(),palyer.getY()));
                    }
                }
                break;
        }
    }

    public void hitPlayer(){
        if(palyer.isLive){
            for(int i = 0; i < enemies.size(); i++){
                Enemies enemy = enemies.get(i);
                for(int j = 0; j < enemy.enemyShots.size(); j++){
                    Shot shot = enemy.enemyShots.get(j);
                    if(shot.alive){
                        hitTank(shot,palyer);
                    }
                }
            }
        }
    }
}
