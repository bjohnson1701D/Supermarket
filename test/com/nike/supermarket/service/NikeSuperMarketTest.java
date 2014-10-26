package com.nike.supermarket.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.nike.supermarket.dao.ItemDao;
import com.nike.supermarket.dao.ItemDaoMapImpl;

public class NikeSuperMarketTest {

	private Supermarket market;
	
	@Before
	public void setUp(){
		ItemDao dao = new ItemDaoMapImpl();
		market = new NikeSupermarket(dao);
	}
	
	@Test
	public void checkoutHappyPath(){
		String items = "ABBACBBAB";
		int value = market.checkout(items);
		assertEquals(240, value);
	}
	
	/**
	 * White spaces are removed when calculating total price.
	 */
	@Test
	public void whiteSpaceShouldBeIgnored(){
		String items = "A  BBA   CBB    AB";
		int value = market.checkout(items);
		assertEquals(240, value);
	}
	
	@Test
	public void caseInsensitiveTest(){
		String items = "aBbaCbbAb";
		int value = market.checkout(items);
		assertEquals(240, value);
	}
	
	@Test
	public void emptyCartWillReturnZero(){
		String items = "";
		int value = market.checkout(items);
		assertEquals(0, value);
	}

	@Test
	public void nullWillReturnZero(){
		String items = null;
		int value = market.checkout(items);
		assertEquals(0, value);
	}

	/**
	 * Alphabetic characters not used as item codes will throw an exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void invalidAlphaCharacterThrowException() {
		String items = "ZYXWVUTS";
		market.checkout(items);
	}

	/**
	 * Numeric characters not used as item codes will throw an exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void invalidNumericCharacterThrowException() {
		String items = "12345";
		market.checkout(items);
	}

	/**
	 * Special characters not used as item codes will throw an exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void invalidSpecialCharacterThrowException() {
		String items = "$&%^@*@&";
		market.checkout(items);
	}
}
