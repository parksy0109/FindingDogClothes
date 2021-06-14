package com.phone.catdog.adapter;

public class Data {
    //이미지 경로
    String path;
    //이미지 내용
    String content;
    //가격
    String price;
    //쇼핑몰 경로
    String url;
    //즐겨찾기 여부
    boolean isFav;
    //
    String sort;
    String size;
    String clothsort;

    public Data(String path, String size, String clothsort, String content, String price, String url, boolean isFav) {
        this.path = path;
        this.content = content;
        this.price = price;
        this.size = size;
        this.clothsort = clothsort;
        this.url = url;
        this.isFav = isFav;
    }



    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public String getSort() {return clothsort;}

    public String setSort() {return this.clothsort = clothsort;}

    public String getSize() {return size;}

    public String setSize() {return this.size = size;}

}
