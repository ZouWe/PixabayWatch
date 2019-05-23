package entity;

import java.io.Serializable;

public class ImageInfo implements Serializable {
    private Integer id;
    private String pageURL;
    private String type;
    private String tags;
    private String previewURL;
    private Integer previewWidth;
    private Integer previewHeight;
    private String webformatURL;
    private Integer webformatWidth;
    private Integer webformatHeight;
    private String largeImageURL;
    private String fullHDURL;
    private String imageURL;
    private Integer imageWidth;
    private Integer imageHeight;
    private Integer imageSize;
//    private Integer views;
//    private Integer downloads;
//    private Integer favorites;
//    private Integer likes;
//    private Integer comments;
//    private Integer user_id;
//    private String user;
    private String userImageURL;

    public Integer getId() {
        return id;
    }

    public String getPageURL() {
        return pageURL;
    }

    public String getType() {
        return type;
    }

    public String getTags() {
        return tags;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public Integer getPreviewWidth() {
        return previewWidth;
    }

    public Integer getPreviewHeight() {
        return previewHeight;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public Integer getWebformatWidth() {
        return webformatWidth;
    }

    public Integer getWebformatHeight() {
        return webformatHeight;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public String getFullHDURL() {
        return fullHDURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Integer getImageWidth() {
        return imageWidth;
    }

    public Integer getImageHeight() {
        return imageHeight;
    }

    public Integer getImageSize() {
        return imageSize;
    }

//    public Integer getViews() {
//        return views;
//    }
//
//    public Integer getDownloads() {
//        return downloads;
//    }
//
//    public Integer getFavorites() {
//        return favorites;
//    }
//
//    public Integer getLikes() {
//        return likes;
//    }
//
//    public Integer getComments() {
//        return comments;
//    }
//
//    public Integer getUser_id() {
//        return user_id;
//    }
//
//    public String getUser() {
//        return user;
//    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public void setPreviewWidth(Integer previewWidth) {
        this.previewWidth = previewWidth;
    }

    public void setPreviewHeight(Integer previewHeight) {
        this.previewHeight = previewHeight;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public void setWebformatWidth(Integer webformatWidth) {
        this.webformatWidth = webformatWidth;
    }

    public void setWebformatHeight(Integer webformatHeight) {
        this.webformatHeight = webformatHeight;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public void setFullHDURL(String fullHDURL) {
        this.fullHDURL = fullHDURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    public void setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
    }

//    public void setViews(Integer views) {
//        this.views = views;
//    }
//
//    public void setDownloads(Integer downloads) {
//        this.downloads = downloads;
//    }
//
//    public void setFavorites(Integer favorites) {
//        this.favorites = favorites;
//    }
//
//    public void setLikes(Integer likes) {
//        this.likes = likes;
//    }
//
//    public void setComments(Integer comments) {
//        this.comments = comments;
//    }
//
//    public void setUser_id(Integer user_id) {
//        this.user_id = user_id;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }
}
