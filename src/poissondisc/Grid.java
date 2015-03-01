package poissondisc;

/*
 * Grid upon which the Poisson Disc is generated
 * Grid width = radius/sqrt(2)
 */
public class Grid {
    private final int width;
    private final int height;
    private final int unit;
    private final int rows;
    private final int cols;
    private Square[][] grid;

    public Grid(int width, int height, int unit) {
        this.width = width;
        this.height = height;
        this.unit = unit;
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
        return grid[row][col];
    }

    /*
     * Get the square of the grid that contains the given coordinates
     */
    public Square getSquareContaining(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            int row = (int) Math.floor((double) x / unit);
            int col = (int) Math.floor((double) y / unit);
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
