package com.github.thesolwind;

import com.github.thesolwind.calculator.CalculatorRpn;
import com.github.thesolwind.operation.CalculatorOperationFactoryImpl;
import com.github.thesolwind.parser.InputParameterParserImpl;
import com.github.thesolwind.service.CalculatorServiceImpl;

import java.math.RoundingMode;

public final class ApplicationContext {

    private static final int SCALE = 3;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public static final String WELCOME_PROMPT = "> ";
    public static final String EXIT_CHAR = "q";

    private static final InputParameterParserImpl parser;
    private static final CalculatorRpn calculator;
    private static final CalculatorOperationFactoryImpl operationFactory;
    private static final CalculatorServiceImpl calculatorService;
    private static final Application application;

    static {
        parser = new InputParameterParserImpl();
        calculator = new CalculatorRpn();
        operationFactory = new CalculatorOperationFactoryImpl(SCALE, ROUNDING_MODE);
        calculatorService = new CalculatorServiceImpl(calculator, operationFactory);
        application = new Application(parser, calculatorService);
    }

    private ApplicationContext() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static Application getApplication() {
        return application;
    }
}