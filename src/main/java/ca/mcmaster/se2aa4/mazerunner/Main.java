package ca.mcmaster.se2aa4.mazerunner;

import javax.naming.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    private static final Logger logger = LogManager.getLogger();
    public static void main(String[] args) {


        try {
            Configurations.configure(args);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage()); 
            e.printStackTrace(); 
            System.exit(1);
        }

        logger.info("**** Computing path"); 
        logger.info("PATH NOT COMPUTED"); 
        logger.info("** End of MazeRunner");

    }
}


