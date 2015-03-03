package poissondisc;

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

    public Grid(int width, int height, int unit, double radius) {
        this.width = width;
        this.height = height;
        this.unit = unit;
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
}
