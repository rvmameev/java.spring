package jb.homework.lesson1.repository;

import jb.homework.lesson1.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepository {
	private static final List<Product> store = new ArrayList<>();

	static {
		for (int i = 1; i <= 10; i++) {
			store.add(new Product(i, "title" + i, i + 10));
		}
	}

	public List<Product> getAll() {
		return Collections.unmodifiableList(store);
	}
}
