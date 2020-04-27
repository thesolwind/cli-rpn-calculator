package com.github.thesolwind;

import com.github.thesolwind.calculator.IllegalStateStackException;
import com.github.thesolwind.operation.OperationNotSupportedException;
import com.github.thesolwind.parser.InputParameterParser;
import com.github.thesolwind.parser.Item;
import com.github.thesolwind.service.CalculatorService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

public class Application {

    private final InputParameterParser<String> parser;
    private final CalculatorService<BigDecimal> calculatorService;

    Application(InputParameterParser<String> parser, CalculatorService<BigDecimal> calculatorService) {
        this.parser = parser;
        this.calculatorService = calculatorService;
    }

    /**
     * Here we go!!!
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) throws IOException {
        // Read and calculate incoming lines.
        Application application = ApplicationContext.getApplication();
        application.await(System.in, System.out);
        System.exit(0);
    }

    void await(InputStream inputStream, OutputStream outputStream) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             PrintWriter writer = new PrintWriter(outputStream, true)) {
            String line;
            do {
                writer.printf(ApplicationContext.WELCOME_PROMPT);
                line = reader.readLine();

                if (line == null || ApplicationContext.EXIT_CHAR.equals(line)) {
                    break;
                } else if (line.isEmpty()) {
                    continue;
                }

                try {
                    List<Item> items = parser.parse(line);
                    BigDecimal result = null;
                    for (Item item : items) {
                        result = calculatorService.calculate(item);
                    }
                    writer.println(result);
                } catch (OperationNotSupportedException | IllegalStateStackException e) {
                    writer.println(e.getMessage());
                }
            }
            while (true);
        }
    }

}