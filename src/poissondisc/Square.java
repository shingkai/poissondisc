package poissondisc;

/*
 * Squares that represent the individual cells within the grid
 * Each square may contain at most 1 point, which cannot be changed once set
 */
public class Square {
    private final int unit;
    private final int row;
    private final int col;
    private Point point;

    public Square(int length, int row, int col) {
        this.unit = length;
        this.row = row;
        this.col = col;
    }

    /*
     * Add a point to the square if it does not have one
     * If it already has a point, do nothing and return false
     */
    public boolean addPoint(Point p) {
        if (this.point == null) {
            this.point = p;
            return true;
        } else
            return false;

    }

    /*
     * Return the point contained by this square
     */
    public Point getPoint() {
        return this.point;
    }

    /*
     * Indicates whether or not the square has a point
     */
    public boolean hasPoint() {
        return (this.point != null);
    }

    /*
     * Get the row of the point
     */
    public int getRow() {
        return this.row;
    }

    /*
     * Get the column of the point
     */
    public int getCol() {
        return this.col;
    }

    /*
     * Get the unit (pixel width) of the square
     */
    public int getUnit() {
        return this.unit;
    }

}
