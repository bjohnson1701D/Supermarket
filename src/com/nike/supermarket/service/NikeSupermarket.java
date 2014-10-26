package com.nike.supermarket.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.nike.supermarket.dao.ItemDao;
import com.nike.supermarket.entites.Deal;
import com.nike.supermarket.entites.Item;

/**
 * 
 * @author Brad Johnson
 *
 */
public final class NikeSupermarket implements Supermarket {

	private ItemDao itemDao;
	
	public NikeSupermarket(ItemDao dao) {
		this.itemDao = dao;
	}
	
	/**
	 * Takes a String representation (ex: 'AAABBC') of the items to be purchased and calculates
	 * the total price of those items.
	 * @param items - Items to be purchased
	 * @return Total value items purchased
	 */
	@Override
	public int checkout(String items) {
		if(null==items || items.isEmpty())
			return 0;
		//remove whitespace and capitalizes.
		items = items.replaceAll("\\s", "").toUpperCase();
		Map<Character, Integer> quantities = calculateQuantities(items);
		int total = calculateTotalPrice(quantities);
		return total;
	}

	/**
	 * Takes a map representing the quantity of items being purchased, and
	 * calculates the total price of those items.
	 * @param quantities- Map where key is the item code and value is the quantity for
	 *            that code.
	 * @return
	 */
	private int calculateTotalPrice(Map<Character, Integer> quantities) {
		int sum = 0;
		Iterator<Map.Entry<Character, Integer>> iter = quantities.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<Character, Integer> mapEntry = iter.next();
			sum += calculateItemDealPrice(mapEntry.getKey(),
					mapEntry.getValue());
		}
		return sum;
	}

	/**
	 * Calculates the total price of a given quantity of items based on that
	 * items code. Deals are taken into account when calculating that price.
	 * @param code - Code of the items.
	 * @param qty - Quantity of items.
	 * @return Total cost for the given quantity of items.
	 */
	private int calculateItemDealPrice(char code, int qty) {
		/*
		 * Items could just as easily be retrieved from a database with new persistence class that implements ItemDao.java
		 * The current implementation is retrieving items from Map
		 */
		Item item = itemDao.read(code);
		if(item==null)
			throw new IllegalArgumentException(String.format("Item Code '%s' does not exist", code));
		
		Deal deal = item.getDeal();
		int price = item.getPrice();
		/*
		 * salePrice - determine how many items qualify for the sale price and calculate 
		 * the total sale value of those items.
		 */
		int salePrice = (qty / deal.getNumberOfItems()) * (price * deal.getForThePriceOf());
		/*
		 * fullPrice - calculate the remaining items which do not qualify for the sale price,
		 * and charge full price for those items.
		 */
		int fullPrice = (qty % deal.getNumberOfItems()) * price;
		return salePrice + fullPrice;
	}

	/**
	 * Calculates the quantity of items to be purchased, based on a
	 * <code>String</code> of item codes.
	 * @param items - Items to be purchased
	 * @return Quantity of each item in a Map
	 */
	private Map<Character, Integer> calculateQuantities(String items) {
		Map<Character, Integer> quantities = new HashMap<Character, Integer>();
		for (char code : items.toCharArray()) {
			Integer currentQuantity = quantities.get(code);
			int quantity = currentQuantity == null ? 1 : currentQuantity + 1;
			quantities.put(code, quantity);
		}
		System.out.print("Item Quantities: ");
		System.out.println(quantities);
		return quantities;
	}
	
	
}
