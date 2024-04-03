package ca.mcmaster.se2aa4.mazerunner.Entity;

public class Node {
    private final int X; 
    private final int Y; 


    public Node(int x, int y){
        this.X = x; 
        this.Y = y; 
    }

    public int getX(){return this.X;}
    

    public int getY(){return this.Y;}


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
        Node other = (Node) obj;
        return this.X == other.X && this.Y == other.Y;
    }


    /*
     * Source: https://www.javacodegeeks.com/2019/05/java-equals-hashcode.html
     * Inspiration from javacodegeeks. 
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + X;
        result = prime * result + Y;
        return result;
    }



    @Override
    public String toString() {
    return "(" + this.X + ", " + this.Y + ")";
    }

}
