package ca.mcmaster.se2aa4.mazerunner.Entity;

import ca.mcmaster.se2aa4.mazerunner.Utils.Direction;
import static ca.mcmaster.se2aa4.mazerunner.Utils.Direction.*;


public class Player implements PlayerExplorer {
    private Location currentLocation; 
    private Location exitLocation; 
    private Direction orientation; 


    /**
     * Constructs a Player object with the specified current location, exit location, and orientation.
     * 
     * @param currentLocation The current location of the player in the maze.
     * @param exitLocation The exit location of the maze.
     * @param orientation The orientation of the player (North, South, East, or West).
     */
    public Player(Location currentLocation, Location exitLocation, Direction orientation) {
        this.currentLocation = currentLocation; 
        this.exitLocation = exitLocation; 
        this.orientation = orientation; 
    }


    @Override
    public Direction getOrientation() {
        return this.orientation;
    }


    @Override
    public Location getCurrentLocation() {
        return new Location(this.currentLocation);
    }

    
    @Override
    public Location getExitLocation() {
        return new Location(this.exitLocation);
    }


    /**
     * Turns the player right relative to the current cardinal direction.
     */
    @Override
    public void turnRight() {
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


    /**
     * Turns the player left relative to the current cardinal direction.
     */
    @Override
    public void turnLeft() {
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


    /**
     * Turns the player backwards relative to the current cardinal direction.
     */
    @Override
    public void turnBackwards() {
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


    /**
     * Updates the player's coordinates after moving forward relative to the current cardinal direction.
     */
    @Override
    public void moveForward() {
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
