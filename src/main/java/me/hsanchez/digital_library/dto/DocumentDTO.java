package me.hsanchez.digital_library.dto;

/**
 *
 * @author hsanchez
 */
public class DocumentDTO {
    private int id;
    private String title;
    private double price;
    private int pageNumber;
    
    private FormatDTO format;
    private DeliveryTimeDTO deliveryTime;
    
    private String thumbnailUrl;
    
    private AuthorDTO author;
    private GenreDTO genre;
        
    private EditorialDTO editorial;
    
    private DocumentTypeDTO documentType;

    public DocumentTypeDTO getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentTypeDTO documentType) {
		this.documentType = documentType;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    public FormatDTO getFormat() {
		return format;
	}

	public void setFormat(FormatDTO format) {
		this.format = format;
	}

	public DeliveryTimeDTO getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(DeliveryTimeDTO deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public EditorialDTO getEditorial() {
        return editorial;
    }

    public void setEditorial(EditorialDTO editorial) {
        this.editorial = editorial;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
}
