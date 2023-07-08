import java.util.concurrent.BrokenBarrierException;

public class Zombies extends Beasts{

    int counter = 0;
    private String direction = "right";
    boolean focused = false;
    private boolean isGetDamaged = false;
    int initialLife = 60;

    Zombies(int xCoordinate, int yCoordinate) {
        setxCoordinate(xCoordinate);
        setyCoordinate(yCoordinate);
        setSpeed(1);
        setPower(1);
        setLife(60);
    }

    public void move(){

        int xDiff = (Environment.player.getxCoordinate() - getxCoordinate());
        int yDiff = (Environment.player.getyCoordinate() - getyCoordinate());

         boolean flag = Math.pow(xDiff, 2) + Math.pow(yDiff, 2) < Math.pow(200, 2);
           if (!flag && !focused){
               counter++;
               if (counter < 100){
                   if(getxCoordinate() + getSpeed() < Environment.mapWidth -21) {
                       setxCoordinate(getxCoordinate() + getSpeed());
                       direction = "right";
                   }
               }
               else if ((counter > 100) && (counter < 200)){
                   if (getxCoordinate() - getSpeed() > 21) {
                       setxCoordinate(getxCoordinate() - getSpeed());
                       direction = "left";
                   }
               }
               else if (counter > 200){
                   counter = 0;
               }
           }
           else{
               focused = true;
               if (xDiff <= 0) direction = "left";
               else direction = "right";
               if (direction.equals("right")){
                   setxCoordinate(getxCoordinate() + getSpeed());
               }
               else setxCoordinate(getxCoordinate() - getSpeed());

               if (yDiff <= 0) setyCoordinate(getyCoordinate() - getSpeed());
               else setyCoordinate(getyCoordinate() + getSpeed());
           }
    }

    public String getDirection() {
        return direction;
    }

    public void lifeBar(){
        if (isGetDamaged){
            int halfWidth = 25;
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.rectangle(getxCoordinate() , getyCoordinate() + 50, halfWidth, 5);
            StdDraw.filledRectangle(getxCoordinate() , getyCoordinate() + 50, halfWidth * ((float) getLife()/initialLife) , 5);
        }
    }


    public void setGetDamaged(boolean getDamaged) {
        isGetDamaged = getDamaged;
    }
}
