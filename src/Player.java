import java.awt.event.KeyEvent;


public class Player extends Object {

    public static String pDirection = "right";


    Player(int xCoordinate, int yCoordinate, int speed, int width, int height) {
        setxCoordinate(xCoordinate);
        setyCoordinate(yCoordinate);
        setSpeed(speed);
        setHeight(height);
        setWidth(width);
        setPower(3);
        setLife(20);
    }

    public void isMoved() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
            pDirection = "right";
            if(getxCoordinate() + getSpeed() < Environment.width - 300){
                setxCoordinate(getxCoordinate()+getSpeed());
            }
            else{
                if(Environment.mapWidth / 2 + Background.xCoordinate > Environment.width) {
                    Background.xCoordinate = Background.xCoordinate - getSpeed();
                    for(int i = 0; i<20; i++){
                        Environment.allZombies[i].setxCoordinate( Environment.allZombies[i].getxCoordinate() - getSpeed());
                        if (i < 10) Environment.allOctopuses[i].setxCoordinate(Environment.allOctopuses[i].getxCoordinate() - getSpeed());

                    }
                }else if(getxCoordinate() + getSpeed() <= Environment.width - getWidth()/2){ // 41 burda karakterin yarı eni ileride değişir playerının datatypeı olarak bir şey ekleyecğeim
                    setxCoordinate(getxCoordinate()+getSpeed());
                }
            }
        }
        else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
            pDirection ="left";
            if(getxCoordinate() -getSpeed() > 300){
                setxCoordinate(getxCoordinate()-getSpeed());
            }
            else{
                if(Background.xCoordinate - Environment.mapWidth / 2 < 0 ) {
                    Background.xCoordinate = Background.xCoordinate + getSpeed();
                    for(int i = 0; i<20; i++){
                        Environment.allZombies[i].setxCoordinate( Environment.allZombies[i].getxCoordinate() + getSpeed());
                        if (i < 10)   Environment.allOctopuses[i].setxCoordinate( Environment.allOctopuses[i].getxCoordinate() + getSpeed());

                    }
                }else if(getxCoordinate() -getSpeed() >= getWidth()/2){
                    setxCoordinate(getxCoordinate()-getSpeed());
                }
            }
        }if(StdDraw.isKeyPressed(KeyEvent.VK_UP) && (getyCoordinate() + getSpeed() <= Environment.height -getHeight()/2)){
            setyCoordinate(getyCoordinate() + getSpeed());
        }if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && (getyCoordinate() - getSpeed() >= getHeight()/2)){
            setyCoordinate(getyCoordinate() - getSpeed());
        }
        if(pDirection.equals("right")){
            StdDraw.picture(getxCoordinate(),getyCoordinate(),"player.png",getWidth(),getHeight());
        }else if (pDirection.equals("left")){
            StdDraw.picture(getxCoordinate(),getyCoordinate(),"reverse.png",getWidth(),getHeight());
        }
    }



    public void attack(){
        if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
            if (pDirection.equals("right")) {
                StdDraw.picture(getxCoordinate(), getyCoordinate(), "sword.png", getWidth() + 10, getHeight() + 10);
                for (Zombies zomb : Environment.allZombies) {
                    int xdf = getxCoordinate() - zomb.getxCoordinate();
                    int ydf = getyCoordinate() - zomb.getyCoordinate();
                    if ((xdf <= 0) && ((Math.pow(xdf, 2) + Math.pow(ydf, 2)) <= Math.pow(55, 2))) {
                        zomb.setLife(zomb.getLife() - getPower());
                        zomb.setGetDamaged(true);
                    }
                }
                for(Octopus octop :Environment.allOctopuses){
                    int xdf = getxCoordinate() - octop.getxCoordinate();
                    int ydf = getyCoordinate() - octop.getyCoordinate();
                    if ((xdf <= 0) && ((Math.pow(xdf, 2) + Math.pow(ydf, 2)) <= Math.pow(55, 2))) {
                        octop.setLife(octop.getLife() - getPower());
                        octop.setGetDamaged(true);
                    }
                }

            } else if (pDirection.equals("left")) {
                StdDraw.picture(getxCoordinate(), getyCoordinate(), "sword-reverse.png", getWidth() + 10, getHeight() + 10);
                for (Zombies zomb : Environment.allZombies) {
                    int xdf = getxCoordinate() - zomb.getxCoordinate();
                    int ydf = getyCoordinate() - zomb.getyCoordinate();
                    if ((xdf >= 0) && ((Math.pow(xdf, 2) + Math.pow(ydf, 2)) <= Math.pow(55, 2))) {
                        zomb.setLife(zomb.getLife() - getPower());
                        zomb.setGetDamaged(true);
                    }
                }
                for(Octopus octop :Environment.allOctopuses){
                    int xdf = getxCoordinate() - octop.getxCoordinate();
                    int ydf = getyCoordinate() - octop.getyCoordinate();
                    if ((xdf >= 0) && ((Math.pow(xdf, 2) + Math.pow(ydf, 2)) <= Math.pow(55, 2))) {
                        octop.setLife(octop.getLife() - getPower());
                        octop.setGetDamaged(true);
                    }
                }
            }
        }
    }

}
