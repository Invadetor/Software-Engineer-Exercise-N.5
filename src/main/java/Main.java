import Boundary.ClientPage;
import Boundary.MainFrame;
import  Boundary.ManagerPage;
import  Controller.Controller;
import Database.DatabaseManager;


import javax.swing.SwingUtilities;

public class Main {
    public static void main (String [] args) {

        DatabaseManager.getDatabaseManager().openConnection();

        SwingUtilities.invokeLater(new Runnable() {
            @Override

            public void run() {
                new MainFrame().run();
            }
        });
    }
}
