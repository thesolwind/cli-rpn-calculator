package com.github.thesolwind.service;

import com.github.thesolwind.calculator.Calculator;
import com.github.thesolwind.calculator.IllegalStateStackException;
import com.github.thesolwind.operation.CalculatorOperationFactory;
import com.github.thesolwind.operation.OperationNotSupportedException;
import com.github.thesolwind.parser.Item;
import com.github.thesolwind.parser.ItemTypeEnum;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CalculatorServiceImplTest {

    private CalculatorService<BigDecimal> calculatorService;

    @Mock
    private Calculator<BigDecimal> calculator;

    @Mock
    private CalculatorOperationFactory<String, BigDecimal> operationFactory;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        calculatorService = new CalculatorServiceImpl(calculator, operationFactory);
    }

    @Test
    public void calculate() throws IllegalStateStackException, OperationNotSupportedException {
        String stringValue = "50";
        BigDecimal operandValue = new BigDecimal(stringValue);

        BigDecimal operationValue = BigDecimal.valueOf(50);
        BinaryOperator<BigDecimal> addOperation = BigDecimal::add;

        Mockito.when(calculator.addOperand(operandValue)).thenReturn(operandValue);
        Mockito.when(operationFactory.getOperation("+")).thenReturn(addOperation);
        Mockito.when(calculator.calculateWith(addOperation)).thenReturn(operationValue);

        BigDecimal resultOperand = calculatorService.calculate(new Item(ItemTypeEnum.OPERAND, stringValue));
        BigDecimal resultOperation = calculatorService.calculate(new Item(ItemTypeEnum.OPERATOR, "+"));

        assertNotNull(resultOperand);
        assertNotNull(resultOperation);

        assertEquals(operandValue, resultOperand);
        Mockito.verify(calculator).addOperand(operandValue);

        assertEquals(operationValue, resultOperation);
        Mockito.verify(calculator).calculateWith(addOperation);
    }
}