package com.github.thesolwind.operation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BinaryOperator;

public class CalculatorOperationFactoryImpl implements CalculatorOperationFactory<String, BigDecimal> {

    private static final String MULTIPLE_OPERATOR_SIGN = "*";

    private static final String ADD_OPERATOR_SIGN = "+";

    private static final String DIVIDE_OPERATOR_SIGN = "/";

    private static final String SUBTRACT_OPERATOR_SIGN = "-";

    private final Integer scale;

    private final RoundingMode roundingMode;

    public CalculatorOperationFactoryImpl(Integer scale, RoundingMode roundingMode) {
        this.scale = scale;
        this.roundingMode = roundingMode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BinaryOperator<BigDecimal> getOperation(String operatorSign) throws OperationNotSupportedException {

        String operationSign = operatorSign.trim();

        BinaryOperator<BigDecimal> result;
        switch (operationSign) {
            case MULTIPLE_OPERATOR_SIGN:
                result = BigDecimal::multiply;
                break;
            case ADD_OPERATOR_SIGN:
                result = BigDecimal::add;
                break;
            case DIVIDE_OPERATOR_SIGN:
                result = (o1, o2) -> o1.divide(o2, scale, roundingMode).stripTrailingZeros();
                break;
            case SUBTRACT_OPERATOR_SIGN:
                result = BigDecimal::subtract;
                break;
            default:
                throw new OperationNotSupportedException(String.format("Operator %s not exists", operatorSign));
        }
        return result;
    }
}
