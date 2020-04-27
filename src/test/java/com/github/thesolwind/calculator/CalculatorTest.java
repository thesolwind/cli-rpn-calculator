package com.github.thesolwind.calculator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator<BigDecimal> calculator;

    private final BinaryOperator<BigDecimal> subtract = BigDecimal::subtract;
    private final BinaryOperator<BigDecimal> divide = BigDecimal::divide;

    @Before
    public void setUp() {
        calculator = new CalculatorRpn();
    }

    /**
     * Test case: 5 9 1 - /
     *
     * @throws IllegalStateStackException the exception should not be thrown if test is passed
     */
    @Test
    public void threeOperandsShouldBeAddedAndCalculatesCorrect() throws IllegalStateStackException {

        calculator.addOperand(valueOf(5));
        calculator.addOperand(valueOf(9));
        calculator.addOperand(valueOf(1));

        BigDecimal subtractResult = calculator.calculateWith(subtract);
        assertEquals(valueOf(8), subtractResult);

        BigDecimal divideResult = calculator.calculateWith(divide);
        assertEquals(valueOf(0.625), divideResult);

    }

    /**
     * Test case: 1 -
     *
     * @throws IllegalStateStackException the exception should be thrown if the test is passed.
     */
    @Test(expected = IllegalStateStackException.class)
    public void ifStackHasSingleOperandExceptionShouldBeThrownForAnyOperation() throws IllegalStateStackException {
        calculator.addOperand(valueOf(1));
        calculator.calculateWith(subtract);
    }

    /**
     * Test case: 1 7 -
     *
     * @throws IllegalStateStackException the exception should not be thrown if test is passed
     */
    @Test
    public void twoOperandShouldBeAddedAndCalculateCorrect() throws IllegalStateStackException {
        calculator.addOperand(valueOf(1));
        calculator.addOperand(valueOf(7));
        final BigDecimal subtractResult = calculator.calculateWith(subtract);
        assertEquals(valueOf(-6), subtractResult);
    }

    /**
     * Test case: 1 0 /, we are expecting ArithmeticException because of dividing by zero
     *
     * @throws IllegalStateStackException the exception should not be thrown if test is passed
     */
    @Test(expected = ArithmeticException.class)
    public void dividingByZeroShouldBeThrownException() throws IllegalStateStackException {
        calculator.addOperand(valueOf(1));
        calculator.addOperand(BigDecimal.ZERO);
        calculator.calculateWith(divide);
    }

    /**
     * Test case: /
     *
     * @throws IllegalStateStackException the exception should be thrown if the test is passed.
     */
    @Test(expected = IllegalStateStackException.class)
    public void ifStackIsEmptyExceptionShouldBeThrownForAnyOperation() throws IllegalStateStackException {
        calculator.calculateWith(divide);
    }

}