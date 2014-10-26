package com.nike.supermarket.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nike.supermarket.entites.Deal;
import com.nike.supermarket.entites.Item;

/**
 * Implementation of ItemDao for which CRUD operations will be performed against a local Map, as 
 * opposed a Database.
 * @author Brad Johnson
 */
public class ItemDaoMapImpl implements ItemDao{
	private Map<Character, Item> localMap;
	
	/**
	 * Sets up ItemDaoMapImpl and adds several items to the map which will be used
	 * to test with.
	 */
	public ItemDaoMapImpl(){
		localMap = new HashMap<Character, Item>();
		localMap.put('A', new Item('A', "Item A", "This is item 'A'", new Deal(null,1,1), 20));
		localMap.put('B',new Item('B', "Item B", "This is item 'B'", new Deal("5 for the price of 3",5,3), 50));
		localMap.put('C', new Item('C', "Item C", "This is item 'C'", new Deal(null,1,1), 30));
	}

	@Override
	public Item create(Item item) {
		localMap.put(item.getCode(), item);
		return item;
	}

	@Override
	public Item read(char code) {
		return localMap.get(code);
	}

	@Override
	public Item update(Item item) {
		localMap.put(item.getCode(), item);
		return item;
	}

	@Override
	public boolean delete(char code) {
		Item deleted = localMap.remove(code);
		return deleted != null ? true : false;
	}

	@Override
	public boolean delete(Item item) {
		Item deleted = localMap.remove(item.getCode());
		return deleted != null ? true : false;
	}

	@Override
	public List<Item> readAll() {
		return new ArrayList<Item>(localMap.values());
	}
}
