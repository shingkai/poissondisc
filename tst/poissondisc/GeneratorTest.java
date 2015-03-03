package poissondisc;

import org.junit.*;
import org.junit.rules.*;
import org.apache.commons.cli.*;

public class GeneratorTest {

	@Test
	public void testGenerator() {
		Generator.main(new String[] { "-i", "resources/image.jpg", "-o",
				"resources.output.jpg" });
		Generator.main(new String[] { "-i", "resources/image.jpg", "-o",
				"resources.output.jpg", "-r", "20", "-k", "15" });
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// @Test
	// public void testGeneratorMissingArgument() {
	// thrown.expect(org.apache.commons.cli.MissingArgumentException.class);
	// Generator.main(new String[] { "-i", "resources/image.jpg", "-o" });
	// }

	// @Test (expected = MissingOptionException.class)
	// public void testGeneratorMissingOption() {
	// Generator.main(new String[] { "-i", "resources/image.jpg"});
	// }
	//
	// @Test (expected = NumberFormatException.class)
	// public void testGeneratorParseRadiusFail() {
	// Generator.main(new String[] { "-i", "resources/image.jpg", "-o",
	// "resources.output.jpg", "-r", "ten"});
	// }
	//
	// @Test (expected = NumberFormatException.class)
	// public void testGeneratorParseItersFail() {
	// Generator.main(new String[] { "-i", "resources/image.jpg", "-o",
	// "resources.output.jpg", "-k", "5.5"});
	// }
}
