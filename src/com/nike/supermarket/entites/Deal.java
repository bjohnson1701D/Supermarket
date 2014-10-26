package com.nike.supermarket.entites;

/**
 * 
 * @author Brad Johnson
 */
public class Deal {

	private String name;
	private int numberOfItems;
	private int forThePriceOf;
	
	public Deal(String name, int numberOfItems, int forThePriceOf) {
		super();
		this.name = name;
		this.numberOfItems = numberOfItems;
		this.forThePriceOf = forThePriceOf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public int getForThePriceOf() {
		return forThePriceOf;
	}

	public void setForThePriceOf(int forThePriceOf) {
		this.forThePriceOf = forThePriceOf;
	}
}
