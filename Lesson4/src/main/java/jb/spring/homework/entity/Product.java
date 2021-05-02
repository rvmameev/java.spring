package jb.spring.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	private Long id;
	private String title;
	private BigDecimal cost;
	private Long version;
	private Boolean isDeleted;
}
