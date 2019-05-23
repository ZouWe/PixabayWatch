package entity;

public class BaseVideo {
     String url;
     int width;
     int height;
     int size;

     public void setUrl(String url) {
          this.url = url;
     }

     public void setWidth(int width) {
          this.width = width;
     }

     public void setHeight(int height) {
          this.height = height;
     }

     public void setSize(int size) {
          this.size = size;
     }

     public String getUrl() {
          return url;
     }

     public int getWidth() {
          return width;
     }

     public int getHeight() {
          return height;
     }

     public int getSize() {
          return size;
     }
}
