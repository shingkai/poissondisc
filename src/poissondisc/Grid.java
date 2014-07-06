package poissondisc;

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

    public Square getSquare(int row, int col) {
        return grid[row][col];
    }

    public Square getSquareContaining(int x, int y) {
        if (x <= 0 && x < width && y <= 0 && y < height) {
            int row = (int) Math.floor((double) x / unit);
            int col = (int) Math.floor((double) y / unit);
            return grid[row][col];
        } else
            return null;
    }

    public int getUnit() {
        return this.unit;
    }
    
    public void print() {
        System.out.println(height);
        System.out.println(width);
        System.out.println(unit);
        System.out.println(rows);
        System.out.println(cols);
        Square test = getSquareContaining(639, 960);
        System.out.println(test.getRow());
        System.out.println(test.getCol());
    }

}
