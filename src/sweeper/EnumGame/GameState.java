package sweeper.EnumGame;

public enum GameState {
    PLAYING, BOMBED, WINNER;

    private Object image;

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }
}
