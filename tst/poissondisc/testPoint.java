package poissondisc;

import org.junit.*;

import static org.junit.Assert.*;

public class testPoint {
    Point testPoint;
    private static final int TEST_ITERS = 100000;
    private static final double DELTA = 1e-15;

    @Before
    public void setUp() {
        testPoint = new Point(0, 0);
    }

    @After
    public void tearDown() {
        testPoint = null;
    }

    @Test
    public void testPoint() {
        assertEquals(0, testPoint.getX());
        assertEquals(0, testPoint.getY());

        testPoint = new Point(0, 1);
        assertEquals(0, testPoint.getX());
        assertEquals(1, testPoint.getY());

        assertFalse(testPoint.setXY(-1, -1));
        assertEquals(0, testPoint.getX());
        assertEquals(1, testPoint.getY());

        assertTrue(testPoint.setXY(2, 3));
        assertEquals(2, testPoint.getX());
        assertEquals(3, testPoint.getY());

        assertFalse(testPoint.setXY(-1, 4));
        assertEquals(2, testPoint.getX());
        assertEquals(3, testPoint.getY());

        assertFalse(testPoint.setXY(5, -1));
        assertEquals(2, testPoint.getX());
        assertEquals(3, testPoint.getY());
    }

    @Test
    public void testPointRGB() {
        assertEquals(0, testPoint.getRGB());

        assertFalse(testPoint.setRGB(0x1FFFFFF));
        assertEquals(0, testPoint.getRGB());

        assertFalse(testPoint.setRGB(-1));
        assertEquals(0, testPoint.getRGB());

        assertTrue(testPoint.setRGB(0x555555));
        assertEquals(0x555555, testPoint.getRGB());
    }

    @Test
    public void testPointGetSquare() {
        Grid testGrid = new Grid(1024, 1024, 16, 22.6274);
        Square testSquare;
        testSquare = testPoint.getSquare(testGrid);
        assertEquals(0, testSquare.getRow());
        assertEquals(0, testSquare.getCol());

        assertTrue(testPoint.setXY(16, 16));
        testSquare = testPoint.getSquare(testGrid);
        assertEquals(1, testSquare.getRow());
        assertEquals(1, testSquare.getCol());

        assertTrue(testPoint.setXY(1023, 1023));
        testSquare = testPoint.getSquare(testGrid);
        assertEquals(63, testSquare.getRow());
        assertEquals(63, testSquare.getCol());
    }

    @Test
    public void testPointGetDist() {
        Point testThatPoint = new Point(0,10);
        assertEquals(10, testPoint.getDist(testThatPoint), DELTA);

        testThatPoint = new Point(10,10);
        assertEquals(10*Math.sqrt(2), testPoint.getDist(testThatPoint), DELTA);
        
        testThatPoint = new Point(3,4);
        assertEquals(5, testPoint.getDist(testThatPoint), DELTA);
    }
    
    @Test
    public void testPointGetCandidate() {
        Grid testGrid = new Grid(512, 512, 16, 22.6274);
        assertTrue(testPoint.setXY(256, 256));
        Point testCandidate;
        double dist;
        double r = testGrid.getRadius();

        for (int i = 0; i < TEST_ITERS; i++) {
            testCandidate = testPoint.getCandidate(testGrid);
            dist = testPoint.getDist(testCandidate);
            assertTrue(r <= dist);
        }
    }
}
