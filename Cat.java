import java.util.Random;

public class Cat extends Creature {

    Mouse mouse;
    Creature creature;
    Creature m;
    Creature prey;
    ZombieCat zombieCat;
    boolean starved = false;
    int starveCount = 0;
    int age = 0;
    int x;
    int y;

    public Cat(int x, int y, City cty, Random rnd){
        super(x, y, cty, rnd);
        lab = LAB_YELLOW;
        stepLen=2;
    }

    public char getLab(){
        return 'y';
    }


    public void randomTurn() {
        int number = rand.nextInt(20);
        if (number == 1){
            int dir = rand.nextInt(4);
            setDir(dir);
        }
    }

    public void takeAction(){

        if (starveCount >= 50){ // Changed condition
            int x = getX();
            int y =  getY();
            city.creaturesToAdd.add(new ZombieCat(x, y, city, rand));
            dead = true;
        }

        for (Creature m: city.creatures){
            if (this.dist(m)==0){
                if (m.lab == 'b'){
                    m.dead = true;
                    starveCount = 0;
                }
            }
        }

        if(prey==null || prey.isDead()){
            prey=null;
            this.lab='y';
            this.setPrey();
        }
        else if(!prey.isDead()){ // Changed condition
            int xDiff = Math.abs(getX()-prey.getX());
            int yDiff = Math.abs(getY()-prey.getY());
            if(xDiff<=yDiff){ // Changed condition
                if(getX() >= prey.getX()){ // Changed condition
                    this.setDir(EAST); // Changed direction
                }
                else{
                    this.setDir(WEST);
                }
            }
            else{
                if(getY() <= prey.getY()){ // Changed direction
                    this.setDir(SOUTH);
                }
                else{
                    this.setDir(NORTH);
                }
            }
        }
        starveCount++;
    }

    public void setPrey(){
        for (Creature m: city.creatures){
            if (m.lab == 'b'){
                if (this.dist(m)<= 20){
                    prey = m;
                    this.lab = 'c';
                }
            }
        }
    }
}
