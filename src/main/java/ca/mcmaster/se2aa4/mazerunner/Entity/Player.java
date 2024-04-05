package ca.mcmaster.se2aa4.mazerunner.Entity;

import ca.mcmaster.se2aa4.mazerunner.Utils.Direction;
import static ca.mcmaster.se2aa4.mazerunner.Utils.Direction.*;


public class Player implements PlayerExplorer{
    private Location currentLocation; 
    private Location exitLocation; 
    private Direction orientation; 


    public Player(Location currentLocation, Location exitLocation, Direction orientation){
        this.currentLocation = currentLocation; 
        this.exitLocation = exitLocation; 
        this.orientation = orientation; 
    }

    @Override
    public Direction getOrientation(){
        return this.orientation;
    }


    @Override
    public Location getCurrentLocation(){
        return new Location(this.currentLocation);
    }

    
    @Override
    public Location getExitLocation(){
        return new Location(this.exitLocation);
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
        int newCol;
        int newRow;

        switch (orientation)
        {
            case EAST:
                newCol = this.getCurrentLocation().getY() + 1;
                this.currentLocation.setY(newCol);
                break; 
            case WEST:
                newCol = this.getCurrentLocation().getY() - 1;
                this.currentLocation.setY(newCol);
                break;
            case NORTH: 
                newRow = this.getCurrentLocation().getX() - 1; 
                this.currentLocation.setX(newRow);
                break; 
            case SOUTH:
                newRow = this.getCurrentLocation().getX() + 1; 
                this.currentLocation.setX(newRow);
                break;
            default:
                break;
        }

    }

}
