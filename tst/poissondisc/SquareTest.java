package poissondisc;

import org.junit.*;
import static org.junit.Assert.*;

public class SquareTest {
	Square testSquare;

	@Before
	public void setUp() {
		testSquare = new Square(10,0,1);
	}

	@After
	public void tearDown() {
		testSquare = null;
	}
	
	@Test
	public void testSquare() {
		assertEquals(10, testSquare.getUnit());
		assertEquals(0, testSquare.getRow());
		assertEquals(1, testSquare.getCol());		
	}
	
	@Test
	public void testSquarePoint() {
		assertFalse(testSquare.hasPoint());
		Point testPoint = new Point(0,0);
		assertTrue(testSquare.addPoint(testPoint));
		assertTrue(testSquare.hasPoint());
		Point testSecondPoint = new Point(1,1);
		assertFalse(testSquare.addPoint(testSecondPoint));
		assertTrue(testSquare.hasPoint());
		assertEquals(testPoint, testSquare.getPoint());
	}
}