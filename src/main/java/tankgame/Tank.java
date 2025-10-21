package tankgame;

public class Tank {//创建一个父类tank，后续的玩家坦克和敌人坦克都继承这个类
    private int x;//坦克x坐标
    private int y;//坦克y坐标
    private int direct;//控制坦克方向0表示向上，1表示向右，2表示向下，3表示向左
    private int speed = 1;//坦克移动速度

    //提供改变坦克坐标的方法，面向对象的思维
    public void moveUp() {
        y -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Tank(int X, int Y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
