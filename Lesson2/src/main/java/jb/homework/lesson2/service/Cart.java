package jb.homework.lesson2.service;

import jb.homework.lesson2.model.Product;

import java.util.List;

public interface Cart {
	void add(int productId);

	void remove(int productId);

	List<Product> getProducts();
}
