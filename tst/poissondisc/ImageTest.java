package poissondisc;

import org.junit.*;
import static org.junit.Assert.*;

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
//        assertEquals(null, testImage.getImg());
//        assertEquals(0, testImage.getRGB(0, 0));
//        assertEquals(0, testImage.getWidth());
//        assertEquals(0, testImage.getHeight());
        
        testImage = new Image("resources/image.jpg");
        int rgb = testImage.getRGB(0,0);
        //assertTrue(0 <= rgb && rgb <= 0x00FFFFFF);
        System.out.println(rgb);
        assertEquals(11776, testImage.getWidth());
        assertEquals(1924, testImage.getHeight());
    }
    
    
}
