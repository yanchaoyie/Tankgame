package tankgame;
//把子弹当成一个线程
public class Shot implements Runnable{
    int x;
    int y;
    int direct;//决定子弹方向
    int speed = 2;//子弹速度
    boolean alive = true;//子弹是否存活

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct){
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }//场景大小1000,750
            if(!(x >= 0 && x <= 1000 && y >= 0 && y <= 750)){
                alive = false;
                break;
            }//测试方法是否工作
            System.out.println("子弹的坐标：" + x + " " + y);
        }
    }
}
