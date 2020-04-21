package me.hsanchez.digital_library.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
import me.hsanchez.digital_library.exceptions.PreRequirementException;
import me.hsanchez.digital_library.exceptions.QueryExecutionException;
import me.hsanchez.digital_library.helpers.ImageEncoderHelper;
import me.hsanchez.digital_library.services.DocumentTypeService;
import me.hsanchez.digital_library.services.DocumentsService;
import me.hsanchez.digital_library.services.FormatService;
import me.hsanchez.digital_library.utils.SessionUtils;

/**
 * Servlet implementation class DocumentBasicDataUpdateServlet
 */
@WebServlet("/document/basic-data/update")
public class DocumentBasicDataUpdateServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(DocumentBasicDataUpdateServlet.class.getName());

	private static final long serialVersionUID = 1L;

	private FormatService formatService;
	private DocumentTypeService documentTypeService;
	private DocumentsService documentsService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DocumentBasicDataUpdateServlet() {
		super();
		this.documentTypeService = new DocumentTypeService();
		this.formatService = new FormatService();
		this.documentsService = new DocumentsService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller Start: GET /document/basic-data/update");
		try {
			logger.info("Get target document from session");
			DocumentDTO document = SessionUtils.getDocument(request);
			request.setAttribute("document", document);
		} catch (PreRequirementException e) {
			response.sendRedirect(request.getContextPath() + e.getUrl());
			return;
		}

		List<String> errors = new ArrayList<String>();

		this.renderFormPage(request, response, errors);
		logger.info("Controller End: GET /document/basic-data/update");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Controller Start: POST /document/basic-data/update");
		DocumentDTO prevDocument = null;

		try {
			prevDocument = SessionUtils.getDocument(request);
		} catch (PreRequirementException e) {
			response.sendRedirect(request.getContextPath() + e.getUrl());
			return;
		}

		DocumentDTO document = null;

		try {
			logger.info("Process document");
			document = this.processFormRequest(request);
		} catch (Exception e) {
			logger.severe("Controller Error: " + e.getMessage());
			e.printStackTrace();

			List<String> errors = new ArrayList<String>();
			errors.add("Ha ocurrido un error procesando la imagen.");
			request.setAttribute("hasError", true);
			request.setAttribute("errors", errors);

			this.renderFormPage(request, response, errors);
			return;
		}

		document.setId(prevDocument.getId());
		document.getDeliveryTime().setId(prevDocument.getDeliveryTime().getId());

		try {
			this.documentsService.updateDocumentById(document);
		} catch (QueryExecutionException e) {
			logger.info("Controller Error: " + e.getMessage() + " - " + e.getTechnicalReason());

			List<String> errors = new ArrayList<String>();
			errors.add("Error: " + e.getMessage());

			this.renderFormPage(request, response, errors);
			return;
		}

		logger.info("Controller End: POST /document/basic-data/update");
		response.sendRedirect(request.getContextPath() + "/document/update/detail/" + document.getId());
	}

	private DocumentDTO processFormRequest(HttpServletRequest request) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File repository = (File) this.getServletContext().getAttribute(ServletContext.TEMPDIR);

		factory.setRepository(repository);
		ServletFileUpload upload = new ServletFileUpload(factory);

		Map<String, String> payload = new HashMap<>();
		String thumbnailLink = null;

		List<FileItem> items = upload.parseRequest(request);

		Iterator<FileItem> iterator = items.iterator();
		while (iterator.hasNext()) {
			FileItem item = iterator.next();
			if (item.isFormField()) {
				payload.put(item.getFieldName(), item.getString(StandardCharsets.UTF_8.name()));
			} else {
				logger.info("Processing image");
				File file = new File(repository, System.currentTimeMillis() + item.getName());
				if (!item.getName().isEmpty()) {
					logger.info("Filename: " + item.getName());
					item.write(file);
					thumbnailLink = ImageEncoderHelper.toBase64(file);
				} else {
					logger.info("No new file found");
				}
			}
		}

		logger.info("Convert data to DTO");
		DocumentDTO document = DocumentConverter.toBasicData(payload);
		document.setThumbnailUrl(thumbnailLink);

		return document;
	}

	private void renderFormPage(HttpServletRequest request, HttpServletResponse response, List<String> errors)
			throws ServletException, IOException {
		List<FormatDTO> formats = null;
		List<DocumentTypeDTO> documentTypes = null;

		try {
			logger.info("Get formats");
			formats = this.formatService.getFormats();
			request.setAttribute("formats", formats);
		} catch (QueryExecutionException e) {
			errors.add(e.getMessage());
		}

		try {
			logger.info("Get document types");
			documentTypes = this.documentTypeService.getDocumentTypes();
			request.setAttribute("documentTypes", documentTypes);
		} catch (QueryExecutionException e) {
			errors.add(e.getMessage());
		}

		request.setAttribute("errors", errors);
		request.setAttribute("hasErrors", errors.size() > 0);
		request.getRequestDispatcher("/document/update/basic-data.jsp").forward(request, response);
	}

}
