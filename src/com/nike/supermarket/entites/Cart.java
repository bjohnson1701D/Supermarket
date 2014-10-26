package com.nike.supermarket.entites;

import java.util.ArrayList;
import java.util.List;

/**
 * Cart object for storing Item codes which will be used during
 * the checkout process.
 * @author Brad Johnson
 */
public class Cart {
	List<Character> itemCodes;

	public Cart() {
		itemCodes = new ArrayList<Character>();
	}

	public void addItem(Character item) {
		itemCodes.add(item);
	}

	public void removeItem(Character item) {
		itemCodes.remove(item);
	}

	public void clearCart() {
		itemCodes.clear();
	}
	
	public int size(){
		return itemCodes.size();
	}

	/**
	 * Prints out item codes contained in <code>List&ltCharacter&gt itemCodes</code><br>
	 * Sample Output: 'AAABBBCCC'
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Character c : itemCodes) {
			builder.append(c);
		}
		return builder.toString();
	}
}
