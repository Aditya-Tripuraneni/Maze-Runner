package ca.mcmaster.se2aa4.mazerunner.Utils;

import static ca.mcmaster.se2aa4.mazerunner.Utils.Direction.*;


public class Compass {

    /**
     * Returns the relative direction between current orientation and new orientation.
     * 
     * @param currentOrientation The current orientation.
     * @param newOrientation The new orientation.
     * @return The relative direction (R for right, L for left, F for forward).
     */
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
                break;
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
                break;
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
                break;
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
                break;
            default:
                return F; 
        }

        return F; 
    }
    
}
