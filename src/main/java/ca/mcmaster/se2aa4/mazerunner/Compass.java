package ca.mcmaster.se2aa4.mazerunner;

import static ca.mcmaster.se2aa4.mazerunner.Direction.*;


public class Compass {

    
    public Direction getRelativeDirection(Direction currentOrientation, Direction newOrientation){
        switch (currentOrientation) {
            case NORTH:
                if (newOrientation == EAST) {
                    return R;
                }
                if (newOrientation == WEST) {
                    return L;
                }
                if (newOrientation == NORTH){
                    return F; 
                }
            case SOUTH:
                if (newOrientation == WEST){
                    return R;
                }
                if (newOrientation == EAST){
                    return L; 
                }

                if (newOrientation == SOUTH){
                    return F; 
                }
            case EAST:
                if (newOrientation == SOUTH){
                    return R; 
                }

                if (newOrientation == NORTH){
                    return L; 
                }
                if (newOrientation == EAST){
                    return F; 
                }
            case WEST:
                if (newOrientation == NORTH){
                    return R; 
                }

                if (newOrientation == SOUTH){
                    return L; 
                }

                if (newOrientation == WEST){
                    return F; 
                }
            default:
                return F; 
        }
    }
    
}
