package tankgame;

public class Tank {//创建一个父类tank，后续的玩家坦克和敌人坦克都继承这个类
    private int x;//坦克x坐标
    private int y;//坦克y坐标

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
