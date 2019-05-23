package entity;

import java.util.List;

public class RequestInfo {
    private Integer total;
    private Integer totalHits;
    private List<ImageInfo> hits;

    public Integer getTotal() {
        return total;
    }

    public Integer getTotalHits() {
        return totalHits;
    }

    public List<ImageInfo> getHits() {
        return hits;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
    }

    public void setHits(List<ImageInfo> hits) {
        this.hits = hits;
    }
}
