public class Octopus extends Beasts {
    Octopus(int xCoordinate, int yCoordinate) {
        setxCoordinate(xCoordinate);
        setyCoordinate(yCoordinate);
        setSpeed(1);
        setPower(2);
        setLife(50);
    }
    int counter = 0;
    boolean focused = false;
    private boolean isGetDamaged = false;
    int initialLife = 50;

    public void move(){
        int xDiff = (Environment.player.getxCoordinate() - getxCoordinate());
        int yDiff = (Environment.player.getyCoordinate() - getyCoordinate());
        boolean flag = Math.pow(xDiff, 2) + Math.pow(yDiff, 2) < Math.pow(200, 2);
        if (!flag && !focused){
            counter++;
            if (counter < 100) {
                if (getyCoordinate() + getSpeed() < Environment.height - 42) {
                    setyCoordinate(getyCoordinate() + getSpeed());
                }
            }
            else if ((counter > 100) && (counter < 200)){
                if(getyCoordinate() - getSpeed() > 42) {
                    setyCoordinate(getyCoordinate() - getSpeed());
                }
            }
            else if (counter > 200){
                counter = 0;
            }
        }
        else{
            focused = true;
            String attackDirection = "left";
            if (xDiff <= 0) attackDirection = "left";
            else attackDirection = "right";
            if (attackDirection.equals("right")){
                setxCoordinate(getxCoordinate() + getSpeed());
            }
            else setxCoordinate(getxCoordinate() - getSpeed());

            if (yDiff <= 0) setyCoordinate(getyCoordinate() - getSpeed());
            else setyCoordinate(getyCoordinate() + getSpeed());
        }
    }

    public void lifeBar(){
        if (isGetDamaged){
            int halfWidth = 30;
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.rectangle(getxCoordinate() , getyCoordinate() + 60, halfWidth, 5);
            StdDraw.filledRectangle(getxCoordinate() , getyCoordinate() + 60, halfWidth * ((float) getLife()/initialLife) , 5);
        }
    }
    public void setGetDamaged(boolean getDamaged) {
        isGetDamaged = getDamaged;
    }
}
