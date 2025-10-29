package tankgame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

public class MyReacord {
    private static int record;
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    //传入敌人集合用来保存坦克坐标
    private static Vector<Enemies> enemies = null;
    private static String fileName = "src\\record.txt";
    private static FileReader fr = null;
    private static BufferedReader br = null;
    public static Vector<Node> nodes = new Vector<>();

    public static void setEnemies(Vector<Enemies> enemies){
        MyReacord.enemies = enemies;
    }

    public static void setRecord(int record){
        MyReacord.record = record;
    }
    public static int getRecord(){
        return record;
    }

    public static void writeRecord(){
        try{
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            // 写入击毙敌人数量
            bw.write(record + "");
            bw.newLine();
            // 写入敌人坐标
            for(int i = 0; i < enemies.size(); i++){
                Enemies enemy = enemies.get(i);
                if(enemy.enemyLive) {
                    bw.write(enemy.getX() + " " + enemy.getY() + " " + enemy.getDirect());
                    bw.newLine();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(bw != null){
                    bw.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void readRecord(){
        try{
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            setRecord(Integer.parseInt(br.readLine()));
            String line = null;
            while((line = br.readLine()) != null){
                String[] s = line.split(" ");
                nodes.add(new Node(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])));
            }
        }catch(Exception e){

        }finally{
            if(br != null){
                try{
                    br.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
