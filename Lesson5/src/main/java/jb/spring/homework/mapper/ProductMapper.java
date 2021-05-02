package jb.spring.homework.mapper;

import jb.spring.homework.dto.ProductDto;
import jb.spring.homework.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDto> {
	@Override
	public Product toEntity(ProductDto productDto) {
		if (productDto == null)
			return null;

		return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost(), 1L, false);
	}

	@Override
	public ProductDto toDto(Product product) {
		if (product == null)
			return null;

		return new ProductDto(product.getId(), product.getTitle(), product.getCost());
	}
}
