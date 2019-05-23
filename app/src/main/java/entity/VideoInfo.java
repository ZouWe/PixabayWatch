package entity;

public class VideoInfo {
    int id;
    String pageURL;
    String type;
    String tags;
    int duration;
    String picture_id;
    Videos videos;
    int views;
    int downloads;
    int favorites;
    int likes;
    int comments;
    int user_id;
    String user;
    String userImageURL;

    public void setId(int id) {
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

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPicture_id(String picture_id) {
        this.picture_id = picture_id;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    public int getId() {
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

    public int getDuration() {
        return duration;
    }

    public String getPicture_id() {
        return picture_id;
    }

    public Videos getVideos() {
        return videos;
    }

    public int getViews() {
        return views;
    }

    public int getDownloads() {
        return downloads;
    }

    public int getFavorites() {
        return favorites;
    }

    public int getLikes() {
        return likes;
    }

    public int getComments() {
        return comments;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser() {
        return user;
    }

    public String getUserImageURL() {
        return userImageURL;
    }
}
