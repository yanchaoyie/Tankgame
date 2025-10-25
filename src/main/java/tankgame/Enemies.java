package tankgame;

import java.util.Vector;

public class Enemies extends Tank implements Runnable{
    boolean enemyLive = true;
    //添加子弹集合
    Vector<Shot> enemyShots = new Vector<>();
    Shot shot = null;


    public Enemies(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (enemyLive){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int xOry = (int)Math.floor(Math.random() * 4);
            int randomPath = (int)Math.floor(Math.random() * 60);
            switch (xOry){
                case 0:
                    this.setDirect(0);
                    for(int i = 0; i < randomPath; i++) {
                        try {
                            Thread.sleep(60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(this.getY() > 0) {
                            this.moveUp();
                        }
                    }
                    break;
                case 1:
                    this.setDirect(1);
                    for(int i = 0; i < randomPath; i++) {
                        try {
                            Thread.sleep(60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(this.getX() +  60 < 1000) {
                            this.moveRight();
                        }
                    }
                    break;
                case 2:
                    this.setDirect(2);
                    for(int i = 0; i < randomPath; i++) {
                        try {
                            Thread.sleep(60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(this.getY() + 60 < 750) {
                            this.moveDown();
                        }
                    }
                    break;
                case 3:
                    this.setDirect(3);
                    for(int i = 0; i < randomPath; i++) {
                        try {
                            Thread.sleep(60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(this.getX() > 0) {
                            this.moveLeft();
                        }
                    }
                    break;
            }//令每一个线程的敌人一个随机的时间射子弹
                try {//一秒射一次子弹，一个whlie循环1发子弹，也就是每改变一次方向就会射一发
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                enemyShot();
            }

    }

    //敌人坦克发射子弹
    public void enemyShot(){
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
        enemyShots.add(shot);
        new Thread(shot).start();
    }
}
