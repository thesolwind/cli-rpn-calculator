package com.github.thesolwind.operation;

import java.util.function.BinaryOperator;

public interface CalculatorOperationFactory<T, P> {

    /**
     * Returns operator function by operation sign.
     *
     * @param operatorSign sign operation such as +,-,etc
     * @return the function {@link BinaryOperator} that implements operation.
     * @throws OperationNotSupportedException if operation sign is not supported.
     */
    BinaryOperator<P> getOperation(T operatorSign) throws OperationNotSupportedException;
}
