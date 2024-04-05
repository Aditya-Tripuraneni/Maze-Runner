package ca.mcmaster.se2aa4.mazerunner.Entity;

public class Location {
    private int x; 
    private int y; 


    public Location(int x, int y){
        this.x = x; 
        this.y = y; 
    }


    public Location(Location other) {
        this.x = other.x;
        this.y = other.y;
    }

    public int getX(){return this.x;}

    public void setX(int val){this.x = val;}


    public int getY(){return this.y;}

    public void setY(int val){this.y = val;}



     /*
     * Source: https://www.javacodegeeks.com/2019/05/java-equals-hashcode.html
     * Inspiration from javacodegeeks. 
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Location other = (Location) obj;
        return this.x == other.x && this.y == other.y;
    }


    /*
     * Source: https://www.javacodegeeks.com/2019/05/java-equals-hashcode.html
     * Inspiration from javacodegeeks. 
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }



    @Override
    public String toString() {
    return "(" + this.x + ", " + this.y + ")";
    }

}
