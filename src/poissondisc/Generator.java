package poissondisc;

import org.apache.commons.cli.*;

public class Generator {
    private static final double DEFAULT_RADIUS = 8.0;
    private static final int DEFAULT_ITERS = 10;
    
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
    }

    private void createGrid() {
        this.grid = new Grid(image.getWidth(), image.getHeight(), (int)(radius / Math.sqrt(2)));
        grid.print();
        
    }

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