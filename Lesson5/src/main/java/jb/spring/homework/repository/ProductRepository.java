package jb.spring.homework.repository;

import jb.spring.homework.entity.Product;
import jb.spring.homework.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
	private final SessionFactory sessionFactory;

	public List<Product> getAll() {
		try (Session session = sessionFactory.getCurrentSession()) {
			session.beginTransaction();
			return session.createQuery("select p from Product p where p.isDeleted = :isDeleted", Product.class)
				.setParameter("isDeleted", false)
				.getResultList();
		}
	}

	public Product getById(Long id) {
		try (Session session = sessionFactory.getCurrentSession()) {
			session.beginTransaction();
			return session.get(Product.class, id);
		}
	}

	public Product create(Product product) {
		if (product == null)
			return null;

		try (Session session = sessionFactory.getCurrentSession()) {
			session.beginTransaction();
			session.save(product);
			session.getTransaction().commit();
		}

		return product;
	}

	public Product update(Product product) {
		if (product == null)
			return null;

		Product dbProduct = getById(product.getId());

		if (dbProduct == null)
			throw new EntityNotFoundException(product.getId(), Product.class);

		try (Session session = sessionFactory.getCurrentSession()) {
			session.beginTransaction();
			product.setVersion(dbProduct.getVersion() + 1);
			session.update(product);
			session.getTransaction().commit();
		}

		return product;
	}

	public int deleteById(Long id) {
		try (Session session = sessionFactory.getCurrentSession()) {
			session.beginTransaction();
			List<Product> toDelete = session.createQuery("select p from Product p where p.id = :id", Product.class)
				.setParameter("id", id)
				.getResultList();
			toDelete.forEach(p -> {
				p.setIsDeleted(true);
				session.update(p);
			});
			session.getTransaction().commit();

			return toDelete.size();
		}
	}
}
