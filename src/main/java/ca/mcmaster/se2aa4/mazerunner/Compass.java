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
                if (newOrientation == NORTH){
                    return FORWARD; 
                }
            case SOUTH:
                if (newOrientation == WEST){
                    return RIGHT;
                }
                if (newOrientation == EAST){
                    return LEFT; 
                }

                if (newOrientation == SOUTH){
                    return FORWARD; 
                }
            case EAST:
                if (newOrientation == SOUTH){
                    return RIGHT; 
                }

                if (newOrientation == NORTH){
                    return LEFT; 
                }
                if (newOrientation == EAST){
                    return FORWARD; 
                }
            case WEST:
                if (newOrientation == NORTH){
                    return RIGHT; 
                }

                if (newOrientation == SOUTH){
                    return LEFT; 
                }

                if (newOrientation == WEST){
                    return FORWARD; 
                }
            default:
                return FORWARD; 
        }
    }
    
}
