package navigation.model;

public class Point {
    private final double x;
    private final double y;
    private final int label; // 1 (Engel A) veya -1 (Engel B)

    public Point(double x, double y, int label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public int getLabel() { return label; }
}