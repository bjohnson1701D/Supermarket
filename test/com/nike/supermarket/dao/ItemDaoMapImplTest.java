package com.nike.supermarket.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nike.supermarket.entites.Item;

public class ItemDaoMapImplTest {

	private ItemDao dao;
	
	@Before
	public void setUp(){
		dao = new ItemDaoMapImpl();
	}
	
	@Test
	public void happyPathTest(){
		Item createdItem = new Item('Z', "Item Z", "Test Item", null, 33);
		createdItem = dao.create(createdItem);
		Assert.assertNotNull(createdItem);
		
		Item retrievedItem = dao.read('Z');
		Assert.assertNotNull(retrievedItem);
		Assert.assertEquals(createdItem.getCode(), retrievedItem.getCode());
		retrievedItem.setDescription("New Description");
		
		Item updatedItem = dao.update(new Item(retrievedItem.getCode(), retrievedItem.getName(), "New Desctiption", null, 33));
		Assert.assertNotNull(updatedItem);
		Assert.assertEquals(retrievedItem.getName(), updatedItem.getName());
		Assert.assertNotEquals(createdItem.getDescription(), updatedItem.getDescription());
		
		boolean deleted = dao.delete('Z');
		Assert.assertTrue(deleted);
		
		Item deletedItem = dao.read('Z');
		Assert.assertNull(deletedItem);
	}
	
	@Test
	public void readSuccess(){
		Item i = dao.read('A');
		Assert.assertNotNull(i);
	}
	
	@Test
	public void readNonExistingItemCodeReturnsNull(){
		Item i = dao.read('X');
		Assert.assertNull(i);
	}
	
	@Test
	public void createItemSuccess(){
		Item createdItem = new Item('Z', "Item Z", "Test Item", null, 33);
		createdItem = dao.create(createdItem);
		Assert.assertNotNull(createdItem);
	}
	
	@Test(expected = NullPointerException.class)
	public void createNullItemThrowsNPE(){
		Item nullItem = null;
		dao.create(nullItem);
	}
	
	@Test
	public void deleteSuccess(){
		boolean success = dao.delete('B');
		Assert.assertTrue(success);
		Assert.assertEquals(2, dao.readAll().size());
	}	
	
	@Test
	public void deleteNonExisintItemReturnsFalse(){
		boolean success = dao.delete('F');
		Assert.assertFalse(success);
	}
	
	@Test
	public void readAllSuccess(){
		List<Item> items = dao.readAll();
		Assert.assertEquals(3, items.size());
	}
}
