package ca.mcmaster.se2aa4.mazerunner;


public class Player {
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


    public void moveRight()
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


    public void moveLeft()
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

    public void moveBackWards()
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
                    // player.setCol(player.getCol() + 1); // move east so col ++
                    col++;
                    break; 
            
            case 'W':
                    // player.setCol(player.getCol() -1); // move west so col--
                    col--;
                    break;
            case 'N': 
                    // player.setRow(player.getRow() -1);
                    row--;
                    break; 

            case 'S':
                    // player.setRow(player.getRow() + 1);
                    row++;
                    break;
        }

    }

    


}
