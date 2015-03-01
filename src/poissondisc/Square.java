package poissondisc;

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
    
    public void addPoint(Point p) {
        if (this.point == null)
            this.point = p;
    }
    
    public Point getPoint() {
        return this.point;
    }
    
    public boolean hasPoint() {
        return (this.point != null);
    }
    
    public int getRow() {
        return this.row;
    }
    
    public int getCol() {
        return this.col;
    }
    
    public int getUnit() {
        return this.unit;
    }
    
}
