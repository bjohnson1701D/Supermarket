package com.nike.supermarket.gui;

import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.nike.supermarket.Main;
import com.nike.supermarket.dao.ItemDao;
import com.nike.supermarket.entites.Deal;
import com.nike.supermarket.entites.Item;

public class AddItemDialog {
	
	private ItemDao dao;
	
	public AddItemDialog(ItemDao itemDao){
		this.dao = itemDao;
	}
	
	public void showDialog(){
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.setGroupingUsed(false);
		
        JTextField codeField = new JTextField("E");
        codeField.setDocument(new CharacterField());
        codeField.setText("E");
        JTextField nameField = new JTextField("Item E");
        JTextField descField = new JTextField("This is a new item");
        JTextField priceField = new JFormattedTextField(decimalFormat);
        priceField.setText("25");
        JTextField numberOfItemsField = new JFormattedTextField(numberFormat);
        numberOfItemsField.setText("1");
        JTextField forThePriceOfField = new JFormattedTextField(numberFormat);
        forThePriceOfField.setText("1");
        
	        JPanel panel = new JPanel(new GridLayout(0, 1));
	        panel.add(new JLabel("Item Code:"));
	        panel.add(codeField);
	        panel.add(new JLabel("Name:"));
	        panel.add(nameField);	        
	        panel.add(new JLabel("Description:"));
	        panel.add(descField);
	        panel.add(new JLabel("Price:"));
	        panel.add(priceField);	        
	        panel.add(new JLabel(""));
	        panel.add(new JLabel("Deal Info:"));
	        panel.add(new JLabel("Number of Items:"));
	        panel.add(numberOfItemsField);
	        panel.add(new JLabel("For the price of:"));
	        panel.add(forThePriceOfField);
	        panel.add(new JLabel(""));
	        
	        int result = JOptionPane.showConfirmDialog(null, panel, "Test",
	            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        if (result == JOptionPane.OK_OPTION) {
		        if(codeField.getText().isEmpty() || nameField.getText().isEmpty() || Double.valueOf(priceField.getText())<=0
		        		|| Double.valueOf(numberOfItemsField.getText())<1 || Double.valueOf(numberOfItemsField.getText())<1 ){
		        	JOptionPane.showMessageDialog(null, "One or more fields was invalid, please try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
		        }else{
		        	Item i = new Item(codeField.getText().toUpperCase().charAt(0), nameField.getText(), descField.getText(), 
		        			new Deal("",Double.valueOf(numberOfItemsField.getText()).intValue(),Double.valueOf(numberOfItemsField.getText()).intValue()),
		        			Double.valueOf(priceField.getText()).intValue());
		        	dao.create(i);
		        	Main.getSupermarketPanel().initializeButtons();
		        }
	        } else {
	            System.out.println("Cancelled");
	        }
	}
	
	private  class CharacterField extends PlainDocument {
		private static final long serialVersionUID = 1L;

		@Override
		public void insertString(int offs, String str, AttributeSet a)
				throws BadLocationException {
			if(str != null && (getLength() + str.length() < 2)){
				super.insertString(offs, str, a);
			}
		}
	}
}
