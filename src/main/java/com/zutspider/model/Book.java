package com.zutspider.model;

public class Book {

    private String img;
    private String author;
    private String publisher;
    private String dates;
    private String text;
    private String url;

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

    @Override
    public String toString() {
        return "Book{" +
                "img='" + img + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", dates='" + dates + '\'' +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
