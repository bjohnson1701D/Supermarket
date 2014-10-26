package com.nike.supermarket.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.nike.supermarket.Main;

/**
 * Sets up the Menu Bar to be displayed within the main frame of the applicaiton.
 * @author Brad Johnson
 */
public class SupermarketMenu {

	public static JMenuBar getMenu(){
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem addItem = new JMenuItem("Insert/Update Item");

		exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });  
		
		addItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.showAddItem();
			}
		});
		
		file.add(addItem);
		file.addSeparator();
		file.add(exit);
		
		
		menuBar.add(file);
		return menuBar;
	}
}
