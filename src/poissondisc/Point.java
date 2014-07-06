package poissondisc;

import java.util.Random;

public class Point {
    private int x;
    private int y;
    private int RGB;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getRGB() {
        return this.RGB;
    }

    public void setRGB(int RGB) {
        this.RGB = RGB;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Square getSquare(Grid grid) {
        return grid.getSquareContaining(x, y);
    }

    public Point getCandidate(Grid grid) {
        Random rand = new Random();
        double t = 2 * Math.PI * rand.nextDouble();
        double u = rand.nextDouble() + rand.nextDouble() + rand.nextDouble()
                + rand.nextDouble();
        double v = u;
        if (u > 2)
            v = 4 - u;
        double r = v;
        if (v < 1)
            r = 2 - v;
        int x = this.x + (int)(grid.getUnit() * r * Math.cos(t));
        int y = this.y + (int)(grid.getUnit() * r * Math.sin(t));
        Point candidate = new Point(x, y);
        return candidate;
    }

}
