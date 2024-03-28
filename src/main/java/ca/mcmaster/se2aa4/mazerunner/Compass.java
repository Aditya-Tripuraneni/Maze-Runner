package ca.mcmaster.se2aa4.mazerunner;

import static ca.mcmaster.se2aa4.mazerunner.Direction.*;


public class Compass {

    public Direction getRelativeDirection(Direction currentOrientation, Direction newOrientation){
        switch (currentOrientation) {
            case NORTH:
                if (newOrientation == EAST) {
                    return RIGHT;
                }
                if (newOrientation == WEST) {
                    return LEFT;
                }
            case SOUTH:
                if (newOrientation == WEST){
                    return RIGHT;
                }
                if (newOrientation == EAST){
                    return LEFT; 
                }
            case EAST:
                if (newOrientation == SOUTH){
                    return RIGHT; 
                }

                if (newOrientation == NORTH){
                    return LEFT; 
                }
            case WEST:
                if (newOrientation == NORTH){
                    return RIGHT; 
                }

                if (newOrientation == SOUTH){
                    return LEFT; 
                }
            default:
                return FORWARD; 
        }
    }
    
}
