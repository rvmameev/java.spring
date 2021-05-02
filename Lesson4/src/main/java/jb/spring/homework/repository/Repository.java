package jb.spring.homework.repository;

import java.util.List;

public interface Repository<TEntity, TKey> {
	List<TEntity> getAll();

	TEntity getById(TKey id);
}
