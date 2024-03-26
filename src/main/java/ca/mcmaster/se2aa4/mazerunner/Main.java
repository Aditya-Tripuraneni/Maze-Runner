package ca.mcmaster.se2aa4.mazerunner;


public class Main {

    public static void main(String[] args) {


        try {
            Configurations configurator = new Configurations(args); 
            configurator.configure();
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage()); 
            e.printStackTrace(); 
            System.exit(1);
        }

    }
}


