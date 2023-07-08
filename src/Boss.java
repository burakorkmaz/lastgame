public class Boss extends Beasts{


    public String[] pics = {"octopus.png", "octopus2.png", "octopus3.png"};

    Boss(int xCoordinate, int yCoordinate){
        super(xCoordinate, yCoordinate);
    }
    public void animation(){
        for(String elem:pics){
            StdDraw.clear();
            StdDraw.picture(getxCoordinate(),getyCoordinate(),elem);
            StdDraw.pause(50);
            StdDraw.show();
        }
        for(int i = 2;i >=0;i--){
            StdDraw.clear();
            StdDraw.picture(getxCoordinate(),getyCoordinate(),pics[i]);
            StdDraw.pause(50);
            StdDraw.show();
        }
    }


}
