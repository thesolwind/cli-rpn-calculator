package com.github.thesolwind.calculator;

import java.util.function.BinaryOperator;

public interface Calculator<T> {
    /**
     * Adds operand to calculator context.
     *
     * @param operand the operand of type of generic parameter.
     * @return the added operand.
     */
    T addOperand(T operand);

    /**
     * Applies the operator to calculator context.
     *
     * @param operator the operator.
     * @return the result of operator.
     * @throws IllegalStateStackException in case of calculator context has illegal state.
     */
    T calculateWith(BinaryOperator<T> operator) throws IllegalStateStackException;
}
