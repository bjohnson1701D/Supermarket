package com.nike.supermarket.dao;

import java.util.List;

import com.nike.supermarket.entites.Item;

/**
 * Interface defining methods which will used to perform CRUD operations for Items.
 * @author Brad Johnson
 */
public interface ItemDao {

	/**
	 * Creates a new Item.
	 * @param item - The item to be created
	 * @return the item that was created
	 */
	public Item create(Item item);
	
	/**
	 * Retrieves an item based on the items code.
	 * @param code - code of the item requested.
	 * @return the item requested
	 */
	
	public Item read(char code);
	
	/**
	 * Updates an item.
	 * @param item - the 
	 * @return
	 */
	public Item update(Item item);
	
	/**
	 * Deletes an item, based on the code provided.
	 * @param code - code of the item to be deleted
	 * @return
	 */
	public boolean delete(char code);
	
	/**
	 * deletes an item based on the Item object provided.
	 * @param item - the item to be deleted.
	 * @return
	 */
	public boolean delete(Item item);
	
	/**
	 * Retrieves a list of all available Items.
	 * @return
	 */
	public List<Item> readAll();

}
