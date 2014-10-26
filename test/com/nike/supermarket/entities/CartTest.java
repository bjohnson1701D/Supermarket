package com.nike.supermarket.entities;

import org.junit.Assert;
import org.junit.Test;

import com.nike.supermarket.entites.Cart;

public class CartTest {

	@Test
	public void toStringTest(){
		Cart cart = new Cart();
		cart.addItem('A');
		cart.addItem('B');
		cart.addItem('C');
		Assert.assertEquals("ABC", cart.toString());
	}
}
