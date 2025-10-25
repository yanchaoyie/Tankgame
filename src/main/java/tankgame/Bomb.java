package tankgame;

public class Bomb {
    int x;
    int y;//爆炸图片加载的坐标
    int life = 9;
    boolean isLive = true;
    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
