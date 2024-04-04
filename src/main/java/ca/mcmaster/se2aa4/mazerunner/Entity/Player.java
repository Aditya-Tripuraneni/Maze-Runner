package ca.mcmaster.se2aa4.mazerunner.Entity;

import ca.mcmaster.se2aa4.mazerunner.Utils.Direction;
import static ca.mcmaster.se2aa4.mazerunner.Utils.Direction.*;


public class Player implements PlayerExplorer{
    private int row; 
    private int col; 
    private int exitRow; 
    private int exitCol; 
    private Direction orientation; 


    public Player(int row, int col, int exitRow, int exitCol, Direction orientation){
        this.row = row; 
        this.col = col; 
        this.exitRow = exitRow; 
        this.exitCol = exitCol; 
        this.orientation = orientation; 
    }


    public Direction getOrientation(){
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
    @Override
    public void turnRight()
    {
        switch (orientation)
        {
            case EAST:
                orientation = SOUTH;
                break; 
            case WEST:
                orientation = NORTH;
                break;
            case NORTH: 
                orientation = EAST;
                break;
            case SOUTH:
                orientation = WEST;
                break;
            default:
                break;  
        }
    }


    // turns left relative to cardinal direction
    @Override
    public void turnLeft()
    {
        switch (orientation)
        {
            case EAST:
                orientation = NORTH;
                break; 
            case WEST:
                orientation = SOUTH;
                break;
            case NORTH: 
                orientation = WEST;
                break; 
                
            case SOUTH:
                orientation = EAST;
                break;
            default:
                break; 
        }
    }


    // turns backwards relative to cardinal direction
    @Override
    public void turnBackwards()
    {
        switch (orientation)
        {
            case EAST:
                orientation = WEST;
                break; 
            case WEST:
                orientation = EAST;
                break;
            case NORTH: 
                orientation = SOUTH;
                break; 
            case SOUTH:
                orientation = NORTH;
                break;
            default:
                break;
        }
    }

    
    // updates players coordinates after moving forward relative to cardinal direction
    @Override
    public void moveForward(){

        switch (orientation)
        {
            case EAST:
                col++;
                break; 
            
            case WEST:
                col--;
                break;
            case NORTH: 
                row--;
                break; 

            case SOUTH:
                row++;
                break;
            default:
                break;
        }

    }

}
