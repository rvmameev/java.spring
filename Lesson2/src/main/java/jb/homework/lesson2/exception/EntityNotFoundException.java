package jb.homework.lesson2.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends NullPointerException {
	private int id;
	private Class entityClass;

	public EntityNotFoundException(int id, Class entityClass) {
		super(String.format("Entity not found by %s (%s)", id, entityClass.getName()));
		this.id = id;
		this.entityClass = entityClass;
	}
}
