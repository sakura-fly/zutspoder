package com.zutspider.model;

public class Book {

    private String img;
    private String author;
    private String publisher;
    private String dates;
    private String text;
    private String url;
    private String index;
    private String type;
    private String ISBN;
    private String price;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "img='" + img + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", dates='" + dates + '\'' +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", index='" + index + '\'' +
                ", type='" + type + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
