package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger logger = LogManager.getLogger();
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


