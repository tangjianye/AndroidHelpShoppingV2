package com.product.helpshopping.module.net.response;

/**
 * Created by Administrator on 2016-4-26.
 */
public class HomeItem {
    /**
     * id : 86
     * title : 把身体乳擦起来，找回我们的水嫩肌。
     * cover : http://mask.cloudsdee.com/uploads/kshop/3352084d6f8a55f47cd58f3b7bfdb8f0.png
     * content : 冬天来临后肌肤干燥粗糙的问题也随之而来，保湿护肤就尤为重要。
     * url : http://ai.taobao.com/album/detail.htm?albumId=32290&pid=mm_43541189_10312339_35018693
     * price : 99
     */

    private int id;
    private String title;
    private String cover;
    private String content;
    private String url;
    private String price;

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
