package jb.homework.lesson2.repository;

import jb.homework.lesson2.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository implements Repository<Product> {
	private static final List<Product> store = new ArrayList<>();

	static {
		for (int i = 1; i <= 5; i++) {
			store.add(new Product(i, "title" + i, i + 10));
		}
	}

	public List<Product> getAll() {
		return Collections.unmodifiableList(store);
	}

	public Product getById(int id) {
		return store.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
	}
}
