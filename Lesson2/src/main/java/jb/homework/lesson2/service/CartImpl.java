package jb.homework.lesson2.service;

import jb.homework.lesson2.exception.EntityNotFoundException;
import jb.homework.lesson2.model.Product;
import jb.homework.lesson2.repository.ProductRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CartImpl implements Cart {
	private final List<Product> store = new ArrayList<>();
	private final ProductRepository repository;

	public CartImpl(ProductRepository repository) {
		this.repository = repository;
	}

	public void add(int productId) {
		var product = repository.getById(productId);

		if (product == null)
			throw new EntityNotFoundException(productId, Product.class);

		store.add(product);
	}

	public void remove(int productId) {
		var product = store.stream().filter(p -> p.getId() == productId).findFirst().orElse(null);

		if (product != null) {
			store.remove(product);
		}
	}

	@Override
	public List<Product> getProducts() {
		return Collections.unmodifiableList(store);
	}
}
