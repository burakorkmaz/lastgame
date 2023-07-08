import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
public class Environment {

    public static int width = 1440;
    public static int height = 760;
    public static int mapWidth = width * 3;
    public static Player player = new Player(80,100,2, 80,80);
    public static Zombies[] allZombies = new Zombies[20];
    public static Octopus[] allOctopuses = new Octopus[10];

    public static long firstTime = System.currentTimeMillis();


    public void run(){

        Background background = new Background(mapWidth/2, 380);
        Boss beast = new Boss(700,250);
        Zombies zombie = new Zombies(4087,498);
        zombieGenerator();
        octopusGenerator();

        while(true){
            StdDraw.clear(new Color(255, 255, 102));
            //StdDraw.picture(background.getxCoordinate(),background.getyCoordinate(),"back.jpg",mapWidth,760);
            pictureZombies();
            pictureOctopuses();
            player.isMoved();
            player.attack();
            StdDraw.picture(300,300,"cactus3.png");
            StdDraw.show();
        }
    }


    public void enterance(){
        StdDraw.setCanvasSize(width,height);
        StdDraw.setXscale(0,width);
        StdDraw.setYscale(0,height);
        StdDraw.enableDoubleBuffering();
        while(true) {
            StdDraw.clear((new Color(34,166,153)));
            StdDraw.setPenColor(new Color(242,190,34));
            StdDraw.setFont(new Font("Helvetica",Font.ITALIC, 30));
            StdDraw.text(720, 480, "To start the game press Y, or click the button");
            StdDraw.setPenColor(new Color(242,151,39));
            StdDraw.filledRectangle(720,380,100,30);
            StdDraw.setPenColor(new Color(242,76,61));
            StdDraw.setFont(new Font("Helvetica", Font.BOLD, 40));
            StdDraw.text(720,380,"START");
            StdDraw.show();
            if (StdDraw.isMousePressed()){
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                if (x <= 820 && x>= 620 && y <= 410 && y >= 350){
                    run();
                }
            }
            else if(StdDraw.isKeyPressed(KeyEvent.VK_Y)){
                run();
            }
        }
    }

    public void zombieGenerator(){
        for(int i = 1; i < 21 ;i++){
            int x =  i * 200;
            int y =  (32 + (int) (Math.random() * 698));
            allZombies[i-1] = new Zombies(x,y);
        }
    }
    public void octopusGenerator(){
        for(int i = 1; i < 11 ;i++){
            int x =  i * 420;
            int y =  (32 + (int) (Math.random() * 698));
            allOctopuses[i-1] = new Octopus(x,y);
        }
    }

    public void pictureZombies(){
        for (int i = 0;i < 20;i++){
            if(allZombies[i].getLife() > 0) {
                allZombies[i].lifeBar();
                if (allZombies[i].getDirection().equals("right"))
                    StdDraw.picture(allZombies[i].getxCoordinate(), allZombies[i].getyCoordinate(), "zombie.png", 42, 64);
                if (allZombies[i].getDirection().equals("left"))
                    StdDraw.picture(allZombies[i].getxCoordinate(), allZombies[i].getyCoordinate(), "reverse-zombie.png", 42, 64);
                allZombies[i].move();
            }
        }
    }
    public void pictureOctopuses(){
        for (int i = 0;i < 10;i++){
            if(allOctopuses[i].getLife() > 0) {
                allOctopuses[i].lifeBar();
                StdDraw.picture(allOctopuses[i].getxCoordinate(), allOctopuses[i].getyCoordinate(), "octopus2.png", 94, 84);
                allOctopuses[i].move();
            }
        }

    }

}
