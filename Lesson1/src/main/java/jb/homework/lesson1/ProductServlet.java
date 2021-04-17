package jb.homework.lesson1;

import jb.homework.lesson1.model.Product;
import jb.homework.lesson1.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "productServlet", value = "/products")
public class ProductServlet extends HttpServlet {
	private final ProductRepository repository = new ProductRepository();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder("<html><body><table>");

		sb.append("<tr><th>Id</th><th>Название</th><th>Цена</th><tr>");

		for (Product product : repository.getAll()) {
			sb.append("<tr>");
			sb.append("<td>" + product.getId() + "</td>");
			sb.append("<td>" + product.getTitle() + "</td>");
			sb.append("<td>" + product.getCost() + "</td>");
			sb.append("</tr>");
		}

		sb.append("</table></body></html>");

		resp.setContentType("text/html; charset=utf-8");
		resp.getWriter().println(sb);
	}
}
