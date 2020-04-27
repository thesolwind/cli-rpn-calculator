package com.github.thesolwind.service;

import com.github.thesolwind.calculator.IllegalStateStackException;
import com.github.thesolwind.operation.OperationNotSupportedException;
import com.github.thesolwind.parser.Item;

public interface CalculatorService<T> {

    /**
     * Handles item of calculation.
     *
     * @param item the calculated item can be operand for adding item to context or operation to calculate value.
     * @return the result of calculation or adding operand.
     * @throws OperationNotSupportedException if operation unknown.
     * @throws IllegalStateStackException if state of calculator context is invalid.
     */
    T calculate(Item item) throws OperationNotSupportedException, IllegalStateStackException;

}
