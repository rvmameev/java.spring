package jb.spring.homework.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends NullPointerException {
	private Long id;
	private Class entityClass;

	public EntityNotFoundException(Long id, Class entityClass) {
		super(String.format("Entity not found by %s (%s)", id, entityClass.getName()));
		this.id = id;
		this.entityClass = entityClass;
	}
}
