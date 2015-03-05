package poissondisc;

import java.util.Random;

/*
 * Grid upon which the Poisson Disc is generated
 * Grid width = radius/sqrt(2)
 */
public class Grid {
    private final int width;
    private final int height;
    private final int unit;
    private final double radius;
    private final int rows;
    private final int cols;
    private Square[][] grid;

    public Grid(int width, int height, double radius) {
        this.width = width;
        this.height = height;
        this.unit = (int) (radius / Math.sqrt(2));
        this.radius = radius;
        this.rows = (int) Math.ceil((double) height / unit);
        this.cols = (int) Math.ceil((double) width / unit);
        grid = new Square[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Square(unit, i, j);
            }
        }
    }

    /*
     * Get the square of the grid at given indices
     */
    public Square getSquare(int row, int col) {
        if (0 <= row && row < rows && 0 <= col && col < cols)
            return grid[row][col];
        else
            return null;
    }

    /*
     * Get the square of the grid that contains the given coordinates
     */
    public Square getSquareContaining(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            int col = (int) Math.floor((double) x / unit);
            int row = (int) Math.floor((double) y / unit);
            return grid[row][col];
        } else
            return null;
    }

    /*
     * Check to see if a candidate point occupies a square with another point,
     * and whether or not it is within the radius of existing points. If it is
     * not, assign the candidate to the square
     */
    public boolean assignPoint(Point candidate) {
        int x = candidate.getX();
        int y = candidate.getY();
        int col = this.getColFromX(x);
        int row = this.getRowFromY(y);

        boolean assign = true;

        Square square = this.getSquare(row, col);
        if (square.hasPoint()) {
            return false;
        }

        Square neighbor;
        Point neighborPoint;
        double dist;

        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                neighbor = this.getSquare(i, j);
                if (neighbor != null && neighbor.hasPoint()) {
                    neighborPoint = neighbor.getPoint();
                    dist = candidate.getDist(neighborPoint);
                    if (dist < radius) {
                        assign = false;
                    }
                }
            }
        }

        if (assign)
            square.addPoint(candidate);
        return assign;
    }

    /*
     * Sample a random point from the grid
     */
    public Point samplePoint() {
        Random rand = new Random();
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        return new Point(x, y);
    }

    /*
     * returns true if the point is in the bounds of the grid, else false
     */
    public boolean hasPoint(Point p) {
        int x = p.getX();
        int y = p.getY();
        return (0 <= x && x < width && 0 <= y && y < height);
    }

    /*
     * get the width of the grid
     */
    public int getWidth() {
        return this.width;
    }

    /*
     * get the height of the grid
     */
    public int getHeight() {
        return this.height;
    }

    /*
     * get the unit size (cell width) of the grid
     */
    public int getUnit() {
        return this.unit;
    }

    /*
     * get the radius size (cell diagonal) of the grid
     */
    public double getRadius() {
        return this.radius;
    }

    /*
     * get the number of rows in the grid
     */
    public int getRows() {
        return this.rows;
    }

    /*
     * get the number of columns in the grid
     */
    public int getCols() {
        return this.cols;
    }

    /*
     * get the row that contains the given x
     */
    public int getColFromX(int x) {
        return x / unit;
    }

    /*
     * get the row that contains the given y
     */
    public int getRowFromY(int y) {
        return y / unit;
    }

    /*
     * get the nearest stored point to given a point
     */
    public Point nearestPoint(Point point) {
        int x = point.getX();
        int y = point.getY();
        int col = this.getColFromX(x);
        int row = this.getRowFromY(y);

        Square square = this.getSquare(row, col);
        if (square.hasPoint()) {
            return square.getPoint();
        }

        Square neighbor;

        for (int i = row - 1; i < row + 2; i++) {
            for (int j = col - 1; j < col + 2; j++) {
                neighbor = this.getSquare(i, j);
                if (neighbor != null && neighbor.hasPoint()) {
                    return neighbor.getPoint();
                }
            }
        }
        
        return point;
    }
}
