package com.capstone.exception;

import com.capstone.model.Items;

public class NotEnoughItemsInStockException extends Exception {

	
	private static final long serialVersionUID = 1L;
	   private static final String DEFAULT_MESSAGE = "Not enough products in stock";

	    public NotEnoughItemsInStockException() {
	        super(DEFAULT_MESSAGE);
	    }

	    public NotEnoughItemsInStockException(Items item) {
	        super(String.format("Not enough %s products in stock. Only %d left", item.getItemName(), item.getItemStock()));
	    }

}
