package com.nike.supermarket;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.nike.supermarket.dao.ItemDao;
import com.nike.supermarket.dao.ItemDaoMapImpl;
import com.nike.supermarket.gui.AddItemDialog;
import com.nike.supermarket.gui.SupermarketMenu;
import com.nike.supermarket.gui.SupermarketPanel;
import com.nike.supermarket.service.NikeSupermarket;

/**
 * Main class for Nike Supermarket application.  This class sets up and runs the Swing components
 * associated with the UI of the application.
 * @author Brad Johnson
 */
public class Main {

	private static JFrame frame;
	private static JPanel content;
	private static SupermarketPanel supermarketPanel;
	private static AddItemDialog itemDialog;
	private static ItemDao dao = new ItemDaoMapImpl();

	/**
	 * Main entry point for Nike Supermarket application.  Invokes Main.run().
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main().run();
			}
		});
	}
	
	/**
	 * Sets up Swing UI for Nike Supermarket Applicaiton.
	 */
	private void run(){
		frame = new JFrame("Nike Supermarket");
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		
		content = new JPanel();
		content.setLayout(new CardLayout());
		
		supermarketPanel = new SupermarketPanel(new NikeSupermarket(dao), dao);
		content.add(supermarketPanel);
		itemDialog = new AddItemDialog(dao);
		
		frame.setJMenuBar(SupermarketMenu.getMenu());
		frame.setContentPane(content);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(300, 315);
	}
	

	/**
	 * Shows the popup dialog for creating new Items.
	 */
	public static void showAddItem(){
		itemDialog.showDialog();
	}

	/**
	 * Gets the SupermarketPanel
	 * @return
	 */
	public static SupermarketPanel getSupermarketPanel() {
		return supermarketPanel;
	}
	
	/**
	 * returns the Main frame of this application.
	 * @return
	 */
	public static JFrame getFrame(){
		return frame;
	}	
	
	/**
	 * sets the Main frame of this application.
	 * @return
	 */
	public static void setFrame(JFrame frame_){
		frame = frame_;
	}

}
