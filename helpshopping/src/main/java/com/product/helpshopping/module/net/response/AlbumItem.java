package com.product.helpshopping.module.net.response;

/**
 * Created by Administrator on 2015/11/19 0019.
 */
public class AlbumItem {
    private int id;
    private int typeid;
    private int uid;
    private String username;
    private String title;
    private String cover;
    private String content;
    private String url;
    private String price;
    private int integral;
    private int allnum;
    private int sellnum;
    private int status;

    private long dateline;
    private long begindate;
    private long enddate;

    public int getAllnum() {
        return allnum;
    }

    public void setAllnum(int allnum) {
        this.allnum = allnum;
    }

    public long getBegindate() {
        return begindate;
    }

    public void setBegindate(long begindate) {
        this.begindate = begindate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public long getDateline() {
        return dateline;
    }

    public void setDateline(long dateline) {
        this.dateline = dateline;
    }

    public long getEnddate() {
        return enddate;
    }

    public void setEnddate(long enddate) {
        this.enddate = enddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getSellnum() {
        return sellnum;
    }

    public void setSellnum(int sellnum) {
        this.sellnum = sellnum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "AlbumItem{" +
                "allnum=" + allnum +
                ", id=" + id +
                ", typeid=" + typeid +
                ", uid=" + uid +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", price='" + price + '\'' +
                ", integral=" + integral +
                ", sellnum=" + sellnum +
                ", status=" + status +
                ", dateline=" + dateline +
                ", begindate=" + begindate +
                ", enddate=" + enddate +
                '}';
    }
}
