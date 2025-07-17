package main.java.sweeper;

public enum Box {
    // Все що заходиться під сірою плиткою
    ZERO, NUM1, NUM2, NUM3, NUM4, NUM5, NUM6, NUM7, NUM8, BOMB,
    // Все що заходиться над сірою плиткою
    OPENED, CLOSED, FLAGGED, BOMBED, NOBOMB;

    private Object image;

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }
}
