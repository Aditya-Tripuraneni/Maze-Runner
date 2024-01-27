package ca.mcmaster.se2aa4.mazerunner;


public class Player implements PlayerExplorer{
    private int row; 
    private int col; 
    private int exitRow; 
    private int exitCol; 
    private char orientation; 


    public Player(int row, int col, int exitRow, int exitCol, char orientation){
        this.row = row; 
        this.col = col; 
        this.exitRow = exitRow; 
        this.exitCol = exitCol; 
        this.orientation = orientation; 
    }


    public char getOrientation(){
        return this.orientation;
    }


    public int getRow(){
        return this.row; 
    }


    public int getCol(){
        return this.col; 
    }


    public int getExitRow(){
        return this.exitRow;
    }


    public int getExitCol(){
        return this.exitCol; 
    }


    // turns player right relative to cardinal direction
    public void turnRight()
    {
        switch (orientation)
        {
            case 'E':
                orientation = 'S';
                break; 
            case 'W':
                orientation = 'N';
                break;
            case 'N': 
                orientation = 'E';
                break;
            case 'S':
                orientation = 'W';
                break; 
        }
    }


    // turns left relative to cardinal direction
    public void turnLeft()
    {
        switch (orientation)
        {
            case 'E':
                    orientation = 'N';
                    break; 
            case 'W':
                    orientation = 'S';
                    break;
            case 'N': 
                    orientation = 'W';
                    break; 
                
            case 'S':
                    orientation = 'E';
                    break; 
        }
    }


    // turns backwards relative to cardinal direction
    public void turnBackwards()
    {
        switch (orientation)
        {
            case 'E':
                    orientation = 'W';
                    break; 
            case 'W':
                    orientation = 'E';
                    break;
            case 'N': 
                    orientation = 'S';
                    break; 
            case 'S':
                    orientation = 'N';
                    break;
        }
    }


    public void moveForward(){

        switch (orientation)
        {
            case 'E':
                    col++;
                    break; 
            
            case 'W':
                    col--;
                    break;
            case 'N': 
                    row--;
                    break; 

            case 'S':
                    row++;
                    break;
        }

    }

}
