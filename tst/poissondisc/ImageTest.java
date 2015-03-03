package poissondisc;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;

public class ImageTest {
    Image testImage;
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
        testImage = null;
    }
    
    @Test
    public void testImage() {        
        testImage = new Image("resources/image.jpg");
        int rgb = testImage.getRGB(0,0);
        assertEquals(0xFF4e71d5, rgb);
        assertEquals(11776, testImage.getWidth());
        assertEquals(1924, testImage.getHeight());

        rgb = testImage.getRGB(-1,0);
        assertEquals(-1, rgb);
        
        rgb = testImage.getRGB(-1,-1);
        assertEquals(-1, rgb);

        rgb = testImage.getRGB(0,-1);
        assertEquals(-1, rgb);
        
        rgb = testImage.getRGB(11775,1923);
        assertEquals(0xFFA69277, rgb);
    }    
}
