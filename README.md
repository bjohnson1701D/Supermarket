Supermarket
===========

Supermarket Project:

The Problem:
 
A supermarket sells 3 products listed below: 

1. Product A = $20
2. Product B = $50 (or 5 for the price of 3)
3. Product C = $30
 
Implement the code for a checkout register that calculates the price
of a given sequence of items. The input is a product list as a
String, e.g "ABBACBBAB" : for which the output should be the integer 240.
Please consider testability, documentation, and other good coding
practices in your solution. As an additional challenge, consider how
new pricing rules might be provided programmatically.
 
Implement the following:
```
public interface Supermarket {
   public int checkout(String items);
}
```
-----

New pricing rules can be created throught the UI by navigating to 'File > Insert/Update Item'.  New Items and rules are created via the ItemDaoMapImpl which implements ItemDao.  This could easily be swapped out with a seperate implementation which reads/writes to a database, or web services.  The current implementation stores values in a local Map.

Currently there is only one type of deal that can be created, which is in the form of 'X for the price of Y', addtional deals could also be created and saved via ItemDao, such as 'item X is Y% off'.

