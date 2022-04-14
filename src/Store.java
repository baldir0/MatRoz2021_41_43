public class Store {
    private int x;
    private int y;
    public Store(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int calcArea() {
        return this.x * this.y;
    }
}
