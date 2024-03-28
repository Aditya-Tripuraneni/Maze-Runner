package ca.mcmaster.se2aa4.mazerunner;

public class Node {
    private final int X; 
    private final int Y; 


    public Node(int x, int y){
        this.X = x; 
        this.Y = y; 
    }

    public int getX(){return this.X;}
    

    public int getY(){return this.Y;}


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


    
}
