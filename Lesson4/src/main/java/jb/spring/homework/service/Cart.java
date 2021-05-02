package jb.spring.homework.service;

import jb.spring.homework.entity.Product;

import java.util.List;

public interface Cart {
	void add(Long productId);

	void remove(Long productId);

	List<Product> getProducts();
}
