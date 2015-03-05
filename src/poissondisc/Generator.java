package poissondisc;

import org.apache.commons.cli.*;


/*
 * Generate a Poisson Disc distribution of a given image's size, then re-color
 * the pixels by 1-nearest-neighbor sampling of the Poisson Disc Points
 */
public class Generator {
    private static final double DEFAULT_RADIUS = 8.0;
    private static final int DEFAULT_ITERS = 25;
    
    private final String input;
    private final String output;
    private final double radius;
    private final int iters;
    
    private Image image;
    private Grid grid;
    
    
    public Generator(String input, String output, double radius, int iters) {
        this.input = input;
        this.output = output;
        this.radius = radius;
        this.iters = iters;
        this.image = new Image(input);
        this.createGrid();
        Bridson bridson = new Bridson(grid, iters);
    }

    /*
     * Create a grid the size of the image and with column/row widths such
     * that with all points spaced radius apart, at most 1 point can occupy
     * any one cell
     */ 
    private void createGrid() {
        this.grid = new Grid(image.getWidth(), image.getHeight(), radius);
    }

    /*
     * Get the grid
     */
    public Grid getGrid() {
    	return this.grid;
    }
    
    /*
     * Read in the command line arguments
     * -i : input image file
     * -o : output image file
     * -r : maximum distance between any two points of the Poisson Disc
     * -k : number of points to try when generating new points
     */
    private static Generator parser(String[] args) {
        CommandLineParser parser = new PosixParser();
        String inputVal = "";
        String outputVal = "";
        double radiusVal = DEFAULT_RADIUS;
        int itersVal = DEFAULT_ITERS;
        try {
            CommandLine cmd = parser.parse(poissonOptions(), args);
            inputVal = cmd.getOptionValue("i");
            outputVal = cmd.getOptionValue("o");
            radiusVal = Double.parseDouble(cmd.getOptionValue("r",
                    String.valueOf(DEFAULT_RADIUS)));
            itersVal = Integer.parseInt(cmd.getOptionValue("k",
                    String.valueOf(DEFAULT_ITERS)));
        } catch (ParseException pe) {
            System.err.println("Error : could not parse : " + pe);
            System.exit(0);
        } catch (NumberFormatException ne) {
            System.err.println("Error : could parse number :" + ne);
            System.exit(0);
        }
        return new Generator(inputVal, outputVal, radiusVal, itersVal);
    }

    public static void main(String[] args) {
        Generator generator = parser(args);
    }

    /*
     * Command line options
     * -i : input image file
     * -o : output image file
     * -r : maximum distance between any two points of the Poisson Disc
     * -k : number of points to try when generating new points
     */
    public static Options poissonOptions() {
        Options options = new Options();

        Option inputOp = new Option("i", true, "input file");
        inputOp.setRequired(true);
        options.addOption(inputOp);

        Option outputOp = new Option("o", true, "output file");
        outputOp.setRequired(true);
        options.addOption(outputOp);

        Option radiusOp = new Option("r", true, "minimum radius between points");
        radiusOp.setRequired(false);
        options.addOption(radiusOp);

        Option iterOp = new Option("k", true,
                "max number of iterations to try before passing");
        iterOp.setRequired(false);
        options.addOption(iterOp);

        return options;
    }

}