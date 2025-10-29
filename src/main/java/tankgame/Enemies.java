package tankgame;

import java.util.Vector;

public class Enemies extends Tank implements Runnable{
    boolean enemyLive = true;
    //添加子弹集合
    Vector<Shot> enemyShots = new Vector<>();
    Shot shot = null;
    //添加敌人集合用于判断是否碰撞
    Vector<Enemies> enemies = new Vector<>();

    //从MyPanel传入的敌人集合
    public void setEnemies(Vector<Enemies> enemies) {
        this.enemies = enemies;
    }

    public boolean isTouchEnemyTanks(){
        switch(this.getDirect()){
            case 0:
                for(int i = 0; i < enemies.size(); i++){
                    Enemies enemy = enemies.get(i);
                    if(enemy != this){
                        /*当敌人朝上或朝下时
                        敌人坦克的碰撞箱[enemy.getX(),enemy.getX() + 40] , [enemy.getY(),enemy.getY() + 60]
                        当敌人朝左或右时
                        敌人坦克的碰撞箱[enemy.getX(),enemy.getX() + 60] , [enemy.getY(),enemy.getY() + 40]
                         */
                        if(enemy.getDirect() == 0 || enemy.getDirect() == 2){
                            //需要检测的坦克的位置 this.getX(),this.getY()
                            if(this.getX() > enemy.getX() && this.getX() < enemy.getX() + 40 &&
                            this.getY() > enemy.getY() && this.getY() < enemy.getY() + 60){
                                return true;
                            }
                            //需要检测的坦克的位置 enemy.getX() + 40,enemy.getY()
                            if( this.getX() + 40 > enemy.getX() && this.getX() + 40 < enemy.getX() + 40 &&
                            this.getY() > enemy.getY() && this.getY() < enemy.getY() + 60){
                                return true;
                            }
                        }
                        if(enemy.getDirect() == 1 || enemy.getDirect() == 3){
                            if(this.getX() > enemy.getX() && this.getX() < enemy.getX() + 60 &&
                            this.getY() > enemy.getY() && this.getY() < enemy.getY() + 40){
                                return true;
                            }
                            if(this.getX() + 40 > enemy.getX() && this.getX() + 40 < enemy.getX() + 60 &&
                            this.getY() > enemy.getY() && this.getY() < enemy.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1://坦克朝右
                for(int i = 0; i < enemies.size(); i++){
                    Enemies enemy = enemies.get(i);
                    if(enemy != this){
                        //需要检测的坦克位置this.getX() + 60 , this.getY()
                        if(enemy.getDirect() == 0 || enemy.getDirect() == 2){
                            if(this.getX() + 60> enemy.getX() && this.getX() + 60 < enemy.getX() + 40 &&
                            this.getY() > enemy.getY() && this.getY() < enemy.getY() + 60){
                                return true;
                            }
                            //需要检测的坦克位置this.getX() + 60 , this.getY() + 40
                            if( this.getX() + 60 > enemy.getX() && this.getX() + 60 < enemy.getX() + 40 &&
                             this.getY() + 40> enemy.getY() && this.getY() + 40< enemy.getY() + 60){
                                return true;
                            }
                        }
                        if (enemy.getDirect() == 1 || enemy.getDirect() == 3){
                            if(this.getX() + 60 > enemy.getX() && this.getX() + 60 < enemy.getX() + 60 &&
                            this.getY() > enemy.getY() && this.getY() < enemy.getY() + 40){
                                return true;
                            }
                            if(this.getX() + 60 > enemy.getX() && this.getX() + 60 < enemy.getX() + 60 &&
                            this.getY() + 40> enemy.getY() && this.getY() < enemy.getY() + 40){
                                return true;
                            }

                        }
                    }
                }
                break;
            case 2:
                for(int i = 0; i < enemies.size(); i++){
                    Enemies enemy = enemies.get(i);
                    if(enemy != this){
                        //需要检测的坦克位置this.getX() , this.getY() + 60
                        if(enemy.getDirect() == 0 || enemy.getDirect() == 2){
                            if(this.getX() > enemy.getX() && this.getX() < enemy.getX() + 40 &&
                            this.getY() + 60 > enemy.getY() && this.getY() + 60 < enemy.getY() + 60){
                                return true;
                            }
                            //需要检测的坦克位置this.getX() + 40 , this.getY() + 60
                            if( this.getX() + 40 > enemy.getX() && this.getX() + 40 < enemy.getX() + 40 &&
                             this.getY() + 60 > enemy.getY() && this.getY() + 60 < enemy.getY() + 60){
                                return true;
                            }
                        }
                        if (enemy.getDirect() == 1 || enemy.getDirect() == 3){
                            if(this.getX() > enemy.getX() && this.getX() < enemy.getX() + 60 &&
                            this.getY() + 60 > enemy.getY() && this.getY() + 60 < enemy.getY() + 40){
                                return true;
                            }
                            if( this.getX() + 40 > enemy.getX() && this.getX() + 40 < enemy.getX() + 60 &&
                             this.getY() + 60 > enemy.getY() && this.getY() + 60 < enemy.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                for(int i = 0; i < enemies.size(); i++){
                    Enemies enemy = enemies.get(i);
                    if(enemy != this){
                        //需要检测的坦克位置this.getX() , this.getY()
                        if(enemy.getDirect() == 0 || enemy.getDirect() == 2){
                            if(this.getX() > enemy.getX() && this.getX() < enemy.getX() + 40 &&
                            this.getY() + 40 > enemy.getY() && this.getY() + 40 < enemy.getY() + 60){
                                return true;
                            }
                            //需要检测的坦克位置this.getX() , this.getY() + 40
                            if( this.getX()> enemy.getX() && this.getX() < enemy.getX() + 40 &&
                             this.getY() + 40 > enemy.getY() && this.getY() + 40 < enemy.getY() + 60){
                                return true;
                            }
                        }
                        if (enemy.getDirect() == 1 || enemy.getDirect() == 3){
                            if(this.getX() > enemy.getX() && this.getX() < enemy.getX() + 60 &&
                            this.getY() > enemy.getY() && this.getY() < enemy.getY() + 40){
                                return true;
                            }
                            if( this.getX() > enemy.getX() && this.getX() < enemy.getX() + 60 &&
                             this.getY() + 40 > enemy.getY() && this.getY() + 40 < enemy.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    public Enemies(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (enemyLive) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int xOry = (int) Math.floor(Math.random() * 4);
            int randomPath = (int) Math.floor(Math.random() * 60);

            switch (xOry) {
                case 0:
                    this.setDirect(0);
                    for (int i = 0; i < randomPath; i++) {
                        try {
                            Thread.sleep(60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (this.getY() > 0 && !(isTouchEnemyTanks())) {
                            this.moveUp();
                        }
                    }
                    break;
                case 1:
                    this.setDirect(1);
                    for (int i = 0; i < randomPath; i++) {
                        try {
                            Thread.sleep(60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (this.getX() + 60 < 1000 && !(isTouchEnemyTanks())) {
                            this.moveRight();
                        }
                    }
                    break;
                case 2:
                    this.setDirect(2);
                    for (int i = 0; i < randomPath; i++) {
                        try {
                            Thread.sleep(60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (this.getY() + 60 < 750 && !(isTouchEnemyTanks())) {
                            this.moveDown();
                        }
                    }
                    break;
                case 3:
                    this.setDirect(3);
                    for (int i = 0; i < randomPath; i++) {
                        try {
                            Thread.sleep(60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (this.getX() > 0 && !(isTouchEnemyTanks())) {
                            this.moveLeft();
                        }
                    }
                    break;
            }
        //令每一个线程的敌人一个随机的时间射子弹
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
