package jb.spring.homework.service;

import jb.spring.homework.dto.ProductDto;
import jb.spring.homework.mapper.ProductMapper;
import jb.spring.homework.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository repository;
	private final ProductMapper mapper;

	public List<ProductDto> getAll() {
		return repository.getAll().stream()
			.map(mapper::toDto)
			.collect(Collectors.toList());
	}

	public ProductDto getById(Long id) {
		if (id == null)
			return new ProductDto();

		return mapper.toDto(repository.getById(id));
	}

	public ProductDto save(ProductDto productDto) {
		if (productDto.getId() == null) {
			return mapper.toDto(repository.create(mapper.toEntity(productDto)));
		} else {
			return mapper.toDto(repository.update(mapper.toEntity(productDto)));
		}
	}

	public int deleteById(Long id) {
		return repository.deleteById(id);
	}
}
