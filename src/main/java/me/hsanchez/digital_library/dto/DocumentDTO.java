package me.hsanchez.digital_library.dto;

/**
 *
 * @author hsanchez
 */
public class DocumentDTO {
    private int id;
    private String title;
    private double price;
    private AuthorDTO author;
    private GenreDTO genre;
    private String format;
    private DeliveryTimeDTO deliveryTime;
    private EditorialDTO editorial;
    private int pageNumber;

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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
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
}
