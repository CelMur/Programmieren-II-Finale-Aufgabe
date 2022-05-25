package de.jprojekt.main;

public class Main {

    public static void main(String[] args) {
    	
    	/*
        try {
            Mysql.createDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("Test4");
        */
    	
    	Application app = new Application();
    	Gui gui = new Gui();
    	
    	
    	Launcher l = Launcher.create(gui, app);
    	l.launchLogin();
    	
    	
    	
    }

}
