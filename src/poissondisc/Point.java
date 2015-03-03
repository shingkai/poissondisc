package poissondisc;

import java.util.Random;

/*
 * A single point representing a sampling from a Poisson Disc distribution
 */
public class Point {
    private int x;
    private int y;
    private int RGB;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /*
     * Get the x-coordinate of this point
     */
    public int getX() {
        return this.x;
    }

    /*
     * Get the y-coordinate of this point
     */
    public int getY() {
        return this.y;
    }

    /*
     * Get the RGB color of this point RGB color in the sRGB format (24 bit),
     * e.g. 0xFFRRGGBB
     */
    public int getRGB() {
        return this.RGB;
    }

    /*
     * Set the RGB color in sRGB int format (24 bit) e.g. 0xFFRRGGBB
     */
    public boolean setRGB(int RGB) {
        if (0xFF000000 <= RGB && RGB <= 0xFFFFFFFF) {
            this.RGB = RGB;
            return true;
        } else
            return false;
    }

    /*
     * Set the x and y coordinates of a point
     */
    public boolean setXY(int x, int y) {
        if (0 <= x && 0 <= y) {
            this.x = x;
            this.y = y;
            return true;
        } else
            return false;
    }

    /*
     * Get the square in a grid that contains this point
     */
    public Square getSquare(Grid grid) {
        return grid.getSquareContaining(x, y);
    }

    /*
     * Generate a random uniformly distributed point within the annulus of radii
     * r and 2*r
     */
    public Point getCandidate(Grid grid) {
        Random rand = new Random();
        double r_min = grid.getRadius();

        double r = Math.sqrt(rand.nextDouble() * 3 * Math.pow(r_min, 2) + Math.pow(r_min, 2));
        double t = 2 * Math.PI * rand.nextDouble();

        double X = (r * Math.cos(t));
        double Y = (r * Math.sin(t));
        
        // force it to round away from the base point
        if (X >= 0)
            X = Math.ceil(X);
        else
            X = Math.floor(X);

        if (Y >= 0)
            Y = Math.ceil(Y);
        else
            Y = Math.floor(Y);
        
        int x = this.x + (int) X;
        int y = this.y + (int) Y;
        Point candidate = new Point(x, y);
        return candidate;
    }

    /*
     * Get the distance between two points
     */
    public double getDist(Point that) {
        return Math.sqrt(Math.pow(this.x - that.getX(), 2)
                + Math.pow(this.y - that.getY(), 2));
    }
}
