package jb.spring.homework.repository;

import jb.spring.homework.entity.Product;
import jb.spring.homework.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
	private static final List<Product> store = new CopyOnWriteArrayList<>();
	private static AtomicLong genId = new AtomicLong();

	static {
		for (long i = 1; i <= 5; i++) {
			store.add(new Product(genId.incrementAndGet(), "title" + i, BigDecimal.valueOf(i + 10), 1L, false));
		}
	}

	public List<Product> getAll() {
		return store.stream()
			.filter(p -> !p.getIsDeleted())
			.collect(Collectors.toUnmodifiableList());
	}

	public Product getById(Long id) {
		return store.stream()
			.filter(p -> p.getId().equals(id))
			.findFirst()
			.orElseThrow(() -> new EntityNotFoundException(id, Product.class));
	}

	public Product create(Product product) {
		if (product == null)
			return null;

		product.setId(genId.incrementAndGet());
		store.add(product);

		return product;
	}

	public Product update(Product product) {
		if (product == null)
			return null;

		Product storeProduct = store.stream()
			.filter(p -> p.getId().equals(product.getId()))
			.findFirst()
			.orElseThrow(() -> new EntityNotFoundException(product.getId(), Product.class));

		product.setVersion(storeProduct.getVersion());

		store.add(product);
		store.remove(storeProduct);

		return product;
	}

	public int deleteById(Long id) {
		List<Product> toDelete = store.stream()
			.filter(p -> p.getId().equals(id))
			.collect(Collectors.toList());

		toDelete.forEach(p -> p.setIsDeleted(true));

		return toDelete.size();
	}
}
