package com.nike.supermarket.gui;

import javax.swing.JFrame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nike.supermarket.Main;
import com.nike.supermarket.dao.ItemDao;
import com.nike.supermarket.dao.ItemDaoMapImpl;
import com.nike.supermarket.entites.Cart;
import com.nike.supermarket.service.NikeSupermarket;

public class SupermarketPanelTest {

	private SupermarketPanel panel;
	
	@Before
	public void setUp(){
		Main.setFrame(new JFrame());
		ItemDao dao = new ItemDaoMapImpl();
		panel = new SupermarketPanel(new NikeSupermarket(dao),dao);
		panel.init();
	}
	
	@Test
	public void emptyCartShouldReturnFalse(){
		boolean success = panel.checkOut(false);
		Assert.assertFalse(success);
	}
	
	@Test
	public void nullCartShouldReturnFalse(){
		panel.setCart(null);
		boolean success = panel.checkOut(false);
		Assert.assertFalse(success);
	}
	
	@Test
	public void cartWithItemReturnsTrue(){
		Cart cart = new Cart();
		cart.addItem('A');
		cart.addItem('A');
		cart.addItem('A');
		cart.addItem('B');
		cart.addItem('B');
		cart.addItem('B');
		cart.addItem('B');
		cart.addItem('B');
		cart.addItem('C');
		panel.setCart(cart);
		boolean success = panel.checkOut(false);
		Assert.assertTrue(success);
	}
}
