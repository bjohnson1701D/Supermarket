package com.nike.supermarket.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.nike.supermarket.Main;
import com.nike.supermarket.dao.ItemDao;
import com.nike.supermarket.entites.Cart;
import com.nike.supermarket.entites.Deal;
import com.nike.supermarket.entites.Item;
import com.nike.supermarket.service.Supermarket;

/**
 * Main Panel associated with the Nike Supermarket Application.
 * @author Brad Johnson
 *
 */
public class SupermarketPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Cart cart;
	private Supermarket supermarket;
	private ItemDao itemDao;
	private JLabel cartLabel;
	private JPanel itemPanel;
	
	public SupermarketPanel(Supermarket supermarket_, ItemDao dao_) {
		this.supermarket = supermarket_;
		this.itemDao = dao_;
		init();
	}

	/**
	 * Initializes components on this panel.
	 */
	public void init(){
		this.removeAll();
		this.setLayout(null);//not using layout manager, placing components.
		cart = new Cart();//instantiate the shopping cart.
		
		//Sets up 'add item' label.
		JLabel addLabel = new JLabel("Add Item:");
		addLabel.setBounds(10, 0, 75, 20);
		add(addLabel);

		initializeButtons();
		
		//Sets up the cart label, this will display how many items are currently in your cart.
		cartLabel = new JLabel(cart.size() + " items in your cart");
		cartLabel.setBounds(10, 200, 150, 20);
		add(cartLabel);

		//Sets up the checkout button.  calls the checkOut() method when clicked.
		JButton checkoutButton = new JButton("CHECKOUT");
		checkoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkOut(true);
			}
		});
		checkoutButton.setBounds(10, 225, 100, 20);
		add(checkoutButton);
		
	}

	/**
	 * Loops through all available products and creates a button
	 * for each product.  products are currently defined in
	 * com.nike.supermarket.entities.ItemData.java.
	 */
	public void initializeButtons() {
		if(null!=itemPanel)
			remove(itemPanel);
		
		itemPanel = new JPanel();
		itemPanel.setLayout(new FlowLayout());
		List<Item> allItems = itemDao.readAll();
		
		//Sort list of items based on item code.
		Collections.sort(allItems, new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				Character c1 = o1.getCode();
				Character c2 = o2.getCode();
				return c1.compareTo(c2);
			}
		});
		
		for(final Item item : allItems){
			JButton addButton = new JButton(item.getName());
			Deal deal = item.getDeal();
			String dealName = deal == null ? "" : "<br>" + deal.getName();
			//Set tool tip as description and deal if available
			addButton.setToolTipText("<html>"+item.getDescription()+"<br>Price: $"+item.getPrice()
					+ dealName+"</html>");
			//Add item to cart when pressed, also update cartLabel.
			addButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cart.addItem(item.getCode());
					cartLabel.setText(cart.size()+ " items in your cart");
				}
			});
			//addButton.setBounds(10, frameHeight, 75, 20);
			addButton.setSize(new Dimension(75, 20));
			itemPanel.add(addButton);
		}
		
		itemPanel.setBounds(10, 30, 280, 150);
		add(itemPanel);
		Main.getFrame().setVisible(true);;
	}

	/**
	 * Checks out all items currently in the cart.  If the cart is empty a
	 * warning will be presented, otherwise an information dialog will be presented 
	 * displaying which items were sent to checkout and how much the total was.
	 */
	public boolean checkOut(boolean showMessage) {
		if(null==cart){
			cart = new Cart();
		}
		
		int total = 0;
		boolean success = false;
		String stringOfItems = cart.toString();
		if (stringOfItems.isEmpty()) {
			if(showMessage)
				JOptionPane.showMessageDialog(Main.getFrame(), "Your Cart is Empty",
						"Warning", JOptionPane.WARNING_MESSAGE);
		} else {
			total = supermarket.checkout(stringOfItems);
			cart.clearCart();
			cartLabel.setText(cart.size()+ " items in your cart");
			success = true;
			if(showMessage)
				JOptionPane.showMessageDialog(Main.getFrame(),
						String.format("<html>Items Purchased: %s<br><br>Your total is: $%s</html>"
								,stringOfItems, total), "Total",
						JOptionPane.INFORMATION_MESSAGE);
		}
		return success;
	}
	
	/**
	 * Sets the shopping cart.
	 * @param cart
	 */
	public void setCart(Cart cart){
		this.cart = cart;
	}

}
