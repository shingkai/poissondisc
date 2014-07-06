package poissondisc;

public class Square {
    private final int length;
    private final int row;
    private final int col;
    private Point point;
    
    public Square(int length, int row, int col) {
        this.length = length;
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
    
    public int getLength() {
        return this.length;
    }
    
}
