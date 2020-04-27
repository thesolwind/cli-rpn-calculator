package com.github.thesolwind.operation;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BinaryOperator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CalculatorOperationFactoryImplTest {

    private CalculatorOperationFactory<String, BigDecimal> factory;

    @Before
    public void setUp() {
        factory = new CalculatorOperationFactoryImpl(3, RoundingMode.HALF_UP);
    }

    @Test
    public void gettingDivideOperation() throws OperationNotSupportedException {
        BinaryOperator<BigDecimal> operation = factory.getOperation("/");
        BigDecimal result = operation.apply(BigDecimal.valueOf(10), BigDecimal.valueOf(5));
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(2), result);
    }

    @Test
    public void gettingSubtractOperation() throws OperationNotSupportedException {
        BinaryOperator<BigDecimal> operation = factory.getOperation("-");
        BigDecimal result = operation.apply(BigDecimal.valueOf(2), BigDecimal.valueOf(1));
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(1), result);
    }

    @Test
    public void gettingAddOperation() throws OperationNotSupportedException {
        BinaryOperator<BigDecimal> operation = factory.getOperation("+");
        BigDecimal result = operation.apply(BigDecimal.valueOf(12), BigDecimal.valueOf(8));
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(20), result);
    }

    @Test
    public void gettingMultipleOperation() throws OperationNotSupportedException {
        BinaryOperator<BigDecimal> operation = factory.getOperation("*");
        BigDecimal result = operation.apply(BigDecimal.valueOf(2), BigDecimal.valueOf(50));
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(100), result);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void gettingNotExistsOperation() throws OperationNotSupportedException {
        factory.getOperation("^");
    }

    @Test
    public void dividingShouldBeCorrectIfValueIsDecimal() throws OperationNotSupportedException {
        BinaryOperator<BigDecimal> operation = factory.getOperation("/");
        BigDecimal result = operation.apply(BigDecimal.valueOf(100), BigDecimal.valueOf(3));
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(33.333), result);
    }

}