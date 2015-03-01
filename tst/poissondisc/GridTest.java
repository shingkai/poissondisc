package poissondisc;

import org.junit.*;

public class GridTest {
	Generator testGenerator = new Generator("resources/image.jpg", "resources.output.jpg", 8.0, 10);
	
	@Test
	public void testGrid() {
		Grid testGrid = testGenerator.getGrid();
		Assert.assertEquals(11776, testGrid.getWidth());
		Assert.assertEquals(1924, testGrid.getHeight());
		Assert.assertEquals(6, testGrid.getUnit());
		Assert.assertEquals(1963, testGrid.getCols());
		Assert.assertEquals(321, testGrid.getRows());
		Square testSquare = testGrid.getSquareContaining(0,0);
		Assert.assertEquals(0, testSquare.getCol());
		Assert.assertEquals(0, testSquare.getRow());
		Assert.assertEquals(testGrid.getUnit(), testSquare.getUnit());
	}
    
}
