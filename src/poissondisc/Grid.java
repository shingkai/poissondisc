package poissondisc;

public class Grid {
    private final int width;
    private final int height;
    private final int unit;
    private final int rows;
    private final int cols;
    
    public Grid(int width, int height, int unit) {
        this.width = width;
        this.height = height;
        this.unit = unit;
        this.rows = (int)Math.ceil((double)height / unit);
        this.cols = (int)Math.ceil((double)width / unit);
    }
    
    public void print() {
        System.out.println(width);
        System.out.println(height);
        System.out.println(unit);
        System.out.println(rows);
        System.out.println(cols);
    }
    
}
