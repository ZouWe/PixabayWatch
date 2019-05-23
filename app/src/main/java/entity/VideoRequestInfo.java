package entity;

import java.util.List;

public class VideoRequestInfo {
    int total;
    int totalHits;
    List<VideoInfo> hits;

    public int getTotal() {
        return total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public List<VideoInfo> getHits() {
        return hits;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public void setHits(List<VideoInfo> hits) {
        this.hits = hits;
    }
}
