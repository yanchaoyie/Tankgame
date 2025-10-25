package tankgame;

import java.util.Vector;

//玩家坦克
public class Player extends Tank{
    boolean isLive = true;
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();//线程安全集合用于存放子弹

    public Player(int x, int y) {
        super(x, y);
    }

    //当玩家按下J时，触发发射子弹的行为时调用此方法
    public void shotEnemyTank(){
        switch(getDirect()){
            case 0:
               shot = new Shot(getX() + 20,getY(),0);
               break;
            case 1:
               shot = new Shot(getX() + 60,getY() + 20,1);
               break;
            case 2:
               shot = new Shot(getX() + 20,getY() + 60,2);
               break;
            case 3:
               shot = new Shot(getX(),getY() + 20,3);
               break;
        }
        if(shots.size() < 5) {
            shots.add(shot);
        }
        new Thread(shot).start();
    }
}
