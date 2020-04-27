package com.github.thesolwind.service;

import com.github.thesolwind.calculator.Calculator;
import com.github.thesolwind.calculator.IllegalStateStackException;
import com.github.thesolwind.operation.CalculatorOperationFactory;
import com.github.thesolwind.operation.OperationNotSupportedException;
import com.github.thesolwind.parser.Item;

import java.math.BigDecimal;

public class CalculatorServiceImpl implements CalculatorService<BigDecimal> {

    private final Calculator<BigDecimal> calculator;
    private final CalculatorOperationFactory<String, BigDecimal> operationFactory;

    public CalculatorServiceImpl(Calculator<BigDecimal> calculator, CalculatorOperationFactory<String, BigDecimal> operationFactory) {
        this.calculator = calculator;
        this.operationFactory = operationFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calculate(Item item) throws OperationNotSupportedException, IllegalStateStackException {
        BigDecimal result;
        switch (item.getItemType()) {
            case OPERATOR:
                result = calculator.calculateWith(operationFactory.getOperation(item.getValue()));
                break;
            case OPERAND:
                result = calculator.addOperand(new BigDecimal(item.getValue()));
                break;
            default:
                result = BigDecimal.ZERO;
        }
        return result;
    }
}
