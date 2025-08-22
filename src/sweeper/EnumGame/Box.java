package sweeper.EnumGame;

public enum Box {
    ZERO, NUM1, NUM2, NUM3, NUM4, NUM5, NUM6, NUM7, NUM8, BOMB,
    OPENED, CLOSED, FLAGGED, DETONATED, NOBOMB;

    private Object image;

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Box next() {
        Box[] values  = Box.values();
        int nextOrdinal = (this.ordinal() + 1) % values.length;
        return values[nextOrdinal];
    }

    public int getNumberBox(){
        int index = this.ordinal();
        if (index == Box.ZERO.ordinal() || index > Box.NUM8.ordinal()){
            return -1;
        }
        return index;
    }
}



