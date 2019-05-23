package entity;

public class Videos {
    BaseVideo large;
    BaseVideo medium;
    BaseVideo small;
    BaseVideo tiny;

    public void setLarge(BaseVideo large) {
        this.large = large;
    }

    public void setMedium(BaseVideo medium) {
        this.medium = medium;
    }

    public void setSmall(BaseVideo small) {
        this.small = small;
    }

    public void setTiny(BaseVideo tiny) {
        this.tiny = tiny;
    }

    public BaseVideo getLarge() {
        return large;
    }

    public BaseVideo getMedium() {
        return medium;
    }

    public BaseVideo getSmall() {
        return small;
    }

    public BaseVideo getTiny() {
        return tiny;
    }
}
