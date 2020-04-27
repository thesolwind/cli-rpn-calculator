package com.github.thesolwind.calculator;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BinaryOperator;

public class CalculatorRpn implements Calculator<BigDecimal> {

    private final Deque<BigDecimal> stack = new ArrayDeque<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal addOperand(BigDecimal operand) {
        stack.add(operand);
        return operand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal calculateWith(BinaryOperator<BigDecimal> operator) throws IllegalStateStackException {
        validateStateOfStack();
        return calculate(operator);
    }

    /**
     * Applies an operator to the last two stack's operands.
     *
     * @param operator kind of operations {@link com.github.thesolwind.operation.CalculatorOperationFactory}.
     * @return the result of operations.
     */
    private BigDecimal calculate(BinaryOperator<BigDecimal> operator) {
        BigDecimal secondOperand = stack.removeLast();
        BigDecimal firstOperand = stack.removeLast();
        BigDecimal operationResult = operator.apply(firstOperand, secondOperand);
        stack.addLast(operationResult);
        return operationResult;
    }

    /**
     * Validates state of stack.
     *
     * @throws IllegalStateStackException If stack is empty of has only one operand exception will be thrown.
     */
    private void validateStateOfStack() throws IllegalStateStackException {
        if (stack.isEmpty() || stack.size() == 1)
            throw new IllegalStateStackException(String.format("Stack has not enough operands, operation is not possible, %s", stack));
    }

}
