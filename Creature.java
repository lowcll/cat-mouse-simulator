import java.util.Random;

public class Creature {

    public final static int NORTH = 0;
    public final static int EAST = 1;
    public final static int SOUTH = 2;
    public final static int WEST = 3;
    public final static int NUM_DIRS = 4;
    public final static int[] DIRS = {NORTH,EAST,SOUTH,WEST};

    protected final int[] dirY = {-1,0,1,0};
    protected final int[] dirX = {0, 1, 0, -1};

    public final static char LAB_BLACK='k';
    public final static char LAB_BLUE='b';
    public final static char LAB_RED='r';
    public final static char LAB_YELLOW='y';
    public final static char LAB_ORANGE='o';
    public final static char LAB_PINK='p';
    public final static char LAB_MAGENTA='m';
    public final static char LAB_CYAN='c';
    public final static char LAB_GREEN='g';
    public final static char LAB_GRAY='e';

    private int dir;
    private GridPoint point;
    protected char lab;
    protected Random rand;
    protected City city;
    protected boolean dead;
    protected int stepLen;

    public Creature(int x, int y, City cty, Random rnd){
        point = new GridPoint(x,y);
        city = cty;
        rand = rnd;
        lab = LAB_GRAY;
        dir = rand.nextInt(NUM_DIRS);
        dead= false;
        stepLen=1;
    }

    public boolean isDead(){ return dead;}

    public int getY(){
        return point.y;
    }
    public int getX(){
        return point.x;
    }
    public GridPoint getGridPoint(){
        return new GridPoint(point);
    }

    public char getLab(){
        return lab;
    }
    public void setDir(int dir){
        this.dir = dir;
    }
    public int getDir(){
        return this.dir;
    }

    public int dist(Creature c){
        return point.dist(c.getGridPoint());
    }

    public void randomTurn() {
        int number = rand.nextInt(20);
        if (number == 1){
            this.dir = rand.nextInt(4);
        }
    }

    public void step(){
        int dir = getDir();
        point.x += stepLen*dirX[dir];
        point.y += stepLen*dirY[dir];
    } 

    public void takeAction(){}
    
    public String toString() {
        return ""+this.point.x+" "+this.point.y+" "+lab;
    }
}
