package com.github.thesolwind.parser;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InputParameterParserImplTest {

    private InputParameterParser<String> parser;

    @Before
    public void setUp() throws Exception {
        parser = new InputParameterParserImpl();
    }

    @Test
    public void resultOfParserShouldBeCorrectIfInputValueHasSequenceOfOperandsAndOperation() {
        String input = "5 9 1 6 /";
        List<Item> result = parser.parse(input);
        assertNotNull(result);
        assertEquals(5, result.size());

        assertEquals(ItemTypeEnum.OPERAND, result.get(0).getItemType());
        assertEquals("5", result.get(0).getValue());

        assertEquals(ItemTypeEnum.OPERAND, result.get(1).getItemType());
        assertEquals("9", result.get(1).getValue());

        assertEquals(ItemTypeEnum.OPERAND, result.get(2).getItemType());
        assertEquals("1", result.get(2).getValue());

        assertEquals(ItemTypeEnum.OPERAND, result.get(3).getItemType());
        assertEquals("6", result.get(3).getValue());

        assertEquals(ItemTypeEnum.OPERATOR, result.get(4).getItemType());
        assertEquals("/", result.get(4).getValue());

    }

    @Test
    public void resultOfParserShouldBeCorrectIfInputValueHasSequenceWhereAreMoreThenOneSpaceBetweenOperands() {
        String input = "5                            9  1   6 /               ";
        List<Item> result = parser.parse(input);
        assertNotNull(result);
        assertEquals(5, result.size());

        assertEquals(ItemTypeEnum.OPERAND, result.get(0).getItemType());
        assertEquals("5", result.get(0).getValue());

        assertEquals(ItemTypeEnum.OPERAND, result.get(1).getItemType());
        assertEquals("9", result.get(1).getValue());

        assertEquals(ItemTypeEnum.OPERAND, result.get(2).getItemType());
        assertEquals("1", result.get(2).getValue());

        assertEquals(ItemTypeEnum.OPERAND, result.get(3).getItemType());
        assertEquals("6", result.get(3).getValue());

        assertEquals(ItemTypeEnum.OPERATOR, result.get(4).getItemType());
        assertEquals("/", result.get(4).getValue());

    }

}