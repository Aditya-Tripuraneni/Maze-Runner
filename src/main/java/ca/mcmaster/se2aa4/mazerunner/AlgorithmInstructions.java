 package ca.mcmaster.se2aa4.mazerunner;


public class AlgorithmInstructions {


    /************************************************************************
    * @Method Name: instructForward(StringBuilder string)                    
    * 
    * @Description: Appends the instruction for moving forward ('F') 
    *               to the provided StringBuilder. If the last character 
    *               in the StringBuilder is 'L' or 'R', a space followed 
    *               by 'F' is appended; otherwise, 'F' is appended directly.
    * 
    * @param string: The StringBuilder to which the instruction is appended.
    *************************************************************************/
    public static void instructForward(StringBuilder string){
        if (string.length() > 0 && (string.charAt(string.length() - 1) == 'L' || string.charAt(string.length() - 1) == 'R'))
            string.append(" F"); 
        else
            string.append("F");
    }


    public static void instructRight(StringBuilder string){
        if (string.length() > 0 && (string.charAt(string.length() - 1) == 'L' || string.charAt(string.length() - 1) == 'F'))
            string.append(" R"); 
        else
            string.append("R");
    }


    public static void instructLeft(StringBuilder string){
        if (string.length() > 0 && (string.charAt(string.length() - 1) == 'F' || string.charAt(string.length() - 1) == 'R'))
            string.append(" L"); 
        else
            string.append("L");
    }


    public static void instructBackwards(StringBuilder string){
        if (string.length() > 0 && (string.charAt(string.length() - 1) == 'F' || string.charAt(string.length() - 1) == 'L'))
            string.append(" RR"); 
        else
            string.append("RR");
    }

    
    public static String factoredExpressionPath(String string){
        StringBuilder factoredExpression = new StringBuilder();

        char currLetter = string.charAt(0);
        int count = 0; 

        for (int i = 0; i < string.length(); i ++)
        {
            if (string.charAt(i) == currLetter)
                count++;
            else if(string.charAt(i) != ' ')
            {
                if (count > 1)
                    factoredExpression.append(count).append(currLetter).append(" ");
                else
                    factoredExpression.append(currLetter).append(" ");
                currLetter = string.charAt(i);
                count = 1;

            }
        }

        if (count > 1)
            factoredExpression.append(count).append(currLetter);
        else 
            factoredExpression.append(currLetter);


        return factoredExpression.toString();
    }
    
}
