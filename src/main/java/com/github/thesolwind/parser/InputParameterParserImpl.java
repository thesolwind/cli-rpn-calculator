package com.github.thesolwind.parser;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputParameterParserImpl implements InputParameterParser<String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> parse(String input) {
        return Stream.of(input.split(" "))
                .filter(s -> !s.isEmpty())
                .map(s -> new Item(defineItemType(s), s))
                .collect(Collectors.toList());
    }

    private ItemTypeEnum defineItemType(String value) {
        return NumberUtils.isCreatable(value)
                ? ItemTypeEnum.OPERAND
                : ItemTypeEnum.OPERATOR;
    }
}