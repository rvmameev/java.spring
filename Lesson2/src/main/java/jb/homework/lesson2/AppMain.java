package jb.homework.lesson2;

import jb.homework.lesson2.model.Product;
import jb.homework.lesson2.service.Cart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext("jb.homework.lesson2");

		Cart cart = context.getBean(Cart.class);
		System.out.println("cart hash: " + cart.hashCode());

		cart = context.getBean(Cart.class);
		System.out.println("cart hash: " + cart.hashCode());

		System.out.println("add to cart");

		cart.add(3);
		cart.add(5);

		for (Product product : cart.getProducts()) {
			System.out.println(product);
		}

		System.out.println("remove from cart");

		cart.remove(4);
		cart.remove(5);

		for (Product product : cart.getProducts()) {
			System.out.println(product);
		}
	}
}
