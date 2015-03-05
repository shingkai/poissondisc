package poissondisc;

import java.util.Set;
import java.util.HashSet;
import java.util.Random;

/*
 * Runs Bridson's Algorithm for Fast Poisson Disc Sampling
 */
public class Bridson {
    private Grid grid;
    private final int iters;
    private Set<Point> frontier;
    
    
    public Bridson(Grid grid, int iters) {
        this.grid = grid;
        this.iters = iters;
        frontier = new HashSet<>();  
    }
    
    public void run() {
        frontier.add(grid.samplePoint());
        
        Point current, candidate;
        boolean assigned = false;
        
        while (!frontier.isEmpty()) {
            current = this.sampleFrontier();
            assigned = false;
            
            for (int i = 0; i < iters; i++) {
                candidate = current.getCandidate(grid);
                if (grid.hasPoint(candidate) && grid.assignPoint(candidate)) {
                    frontier.add(candidate);
                    assigned = true;
                    break;
                }
            }
            if (!assigned)
                frontier.remove(current);
        }
    }
    
    private Point sampleFrontier() {
        Random rand = new Random();
        int k = rand.nextInt(frontier.size());
        int i = 0;
        for (Point point : frontier) {
            if (i == k)
                return point;
            i ++;
        }
        return null;
    }

}
