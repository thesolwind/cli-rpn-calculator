package com.github.thesolwind.parser;

import java.util.List;

public interface InputParameterParser<T> {
    /**
     * Parses input data and maps on items.
     *
     * @param input the input which should be parsed.
     * @return the list of parsed items.
     */
    List<Item> parse(T input);
}
