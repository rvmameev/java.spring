package jb.homework.lesson2.repository;

import java.util.List;

public interface Repository<TEntity> {
	List<TEntity> getAll();

	TEntity getById(int id);
}
