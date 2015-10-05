package de.keyservice.boundary;

import java.io.Closeable;
import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.keyservice.controller.PersonController;
import de.keyservice.entity.Person;

@WebServlet(name = "ImageService", urlPatterns = { "/ImageService/*" })
public class ImageService extends HttpServlet {

	@Inject
	private PersonController PersonenControl;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");

		Long id = Long.parseLong(idString.trim());
		
		Person lPerson = PersonenControl.findPersonByID(id);
		byte[] bild = lPerson.getBild();
		
		ServletOutputStream out = null;

		try {
			response.reset();

			out = response.getOutputStream();

			if (bild != null && bild.length != 0) {
				out.write(bild);
			}
			
		} catch (IOException e) {
			System.out.println(">>>" + e.getMessage());
		} finally {
			close(out);
		}

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	// Helpers (can be refactored to public utility class)
	// ----------------------------------------
	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}