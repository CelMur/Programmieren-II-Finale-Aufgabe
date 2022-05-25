package de.jprojekt.main;

import de.jprojekt.utils.Mysql;
import de.jprojekt.view.frames.MainFrame;

import java.sql.SQLException;

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
    	
    	MainFrame frame = new MainFrame();
    	frame.setVisible(true);
    	
    }

}
