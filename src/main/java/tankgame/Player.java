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
        }//限制玩家子弹
        if(shots.size() < 100) {
            shots.add(shot);
        }//限制玩家子弹频率
        if(shots.size()!=0){
            for(int i = 0;i < shots.size() - 1;i++){
                Shot shot = shots.get(i);
                Shot shot1 = shots.get(i + 1);
                if(!(shot.x - shot1.x > 50 || shot.x - shot1.x < -50
                        || shot.y - shot1.y > 50 || shot.y - shot1.y < -50)){
                    shots.remove(shot1);
                }
            }
        }
        new Thread(shot).start();
    }
}
