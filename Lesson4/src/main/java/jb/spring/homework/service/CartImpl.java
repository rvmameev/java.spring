package jb.spring.homework.service;

import jb.spring.homework.entity.Product;
import jb.spring.homework.exception.EntityNotFoundException;
import jb.spring.homework.repository.ProductRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CartImpl implements Cart {
	private final List<Product> store = new ArrayList<>();
	private final ProductRepository repository;

	public CartImpl(ProductRepository repository) {
		this.repository = repository;
	}

	public void add(Long productId) {
		var product = repository.getById(productId);

		if (product == null)
			throw new EntityNotFoundException(productId, Product.class);

		store.add(product);
	}

	public void remove(Long productId) {
		var product = store.stream().filter(p -> p.getId().equals(productId)).findFirst().orElse(null);

		if (product != null) {
			store.remove(product);
		}
	}

	@Override
	public List<Product> getProducts() {
		return Collections.unmodifiableList(store);
	}
}
