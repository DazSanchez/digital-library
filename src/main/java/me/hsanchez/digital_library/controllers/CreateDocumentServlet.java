package me.hsanchez.digital_library.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import me.hsanchez.digital_library.converters.DocumentConverter;
import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.dto.DocumentTypeDTO;
import me.hsanchez.digital_library.dto.FormatDTO;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.helpers.ImageEncoderHelper;
import me.hsanchez.digital_library.services.DocumentTypeService;
import me.hsanchez.digital_library.services.FormatService;
import me.hsanchez.digital_library.utils.SessionUtils;

/**
 * Servlet implementation class AddDocumentServlet
 */
@WebServlet("/document/create")
public class CreateDocumentServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(CreateDocumentServlet.class.getName());

	private static final long serialVersionUID = 1L;

	private FormatService formatService;
	private DocumentTypeService documentTypeService;

	public CreateDocumentServlet() {
		super();
		this.formatService = new FormatService();
		this.documentTypeService = new DocumentTypeService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller Start: GET /document/create");
		boolean loggedIn = SessionUtils.isLoggedIn(request);

		if (!loggedIn) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		List<String> errors = new ArrayList<String>();
		List<FormatDTO> formats = null;
		List<DocumentTypeDTO> documentTypes = null;

		try {
			formats = this.formatService.getFormats();
			request.setAttribute("formats", formats);
		} catch (QueryExecutionException e) {
			errors.add(e.getMessage());
		}

		try {
			documentTypes = this.documentTypeService.getDocumentTypes();
			request.setAttribute("documentTypes", documentTypes);
		} catch (QueryExecutionException e) {
			errors.add(e.getMessage());
		}

		logger.info("Controller End: GET /document/create");
		this.renderFormPage(request, response, errors);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller Start: POST /document/create");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File repository = (File) this.getServletContext().getAttribute(ServletContext.TEMPDIR);

		factory.setRepository(repository);
		ServletFileUpload upload = new ServletFileUpload(factory);

		Map<String, String> payload = new HashMap<>();
		String thumbnailLink = null;

		try {
			logger.info("Parsing request");
			List<FileItem> items = upload.parseRequest(request);

			Iterator<FileItem> iterator = items.iterator();
			while (iterator.hasNext()) {
				FileItem item = iterator.next();
				if (item.isFormField()) {
					payload.put(item.getFieldName(), item.getString());
				} else {
					logger.info("Found thumbnail in request");
					File file = new File(repository, System.currentTimeMillis() + item.getName());
					item.write(file);
					thumbnailLink = ImageEncoderHelper.toBase64(file);
				}
			}
		} catch (Exception e) {
			logger.severe("Error: " + e.getMessage());
			e.printStackTrace();

			List<String> errors = new ArrayList<String>();
			errors.add("Ha ocurrido un error procesando la imagen.");
			request.setAttribute("hasError", true);
			request.setAttribute("errors", errors);

			this.renderFormPage(request, response, errors);
			return;
		}

		DocumentDTO document = DocumentConverter.toDTO(payload);
		document.setThumbnailUrl(thumbnailLink);

		logger.info("Set document to request");
		request.getSession(false).setAttribute("document", document);

		logger.info("Controller End: POST /document/create");
		response.sendRedirect(request.getContextPath() + "/document/create/author-coincidences");
	}
	
	private void renderFormPage(HttpServletRequest request, HttpServletResponse response, List<String> errors) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/document/create.jsp");
		request.setAttribute("errors", errors);
		request.setAttribute("hasErrors", errors.size() > 0);
		dispatcher.forward(request, response);
	}

}
