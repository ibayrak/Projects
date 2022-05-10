package projeOdevi2;
import javax.swing.JOptionPane;
import java.util.*;				//import declarations
import java.io.*;
public class App {



    public static void main(String [] args) throws IOException
    {
    //Calling welcome Screen
 	Procedure.message();
 	//Game object
    Game g = new Game();
    //Play game method
    g.playGame();

    }


}