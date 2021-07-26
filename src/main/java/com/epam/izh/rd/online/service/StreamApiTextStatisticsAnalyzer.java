package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text).
                stream().
                mapToInt(String::length).
                sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return (int) getWords(text).stream().count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return (int) getUniqueWords(text).stream().count();
    }

    @Override
    public List<String> getWords(String text) {
        return Arrays.stream(text.split("\\W+")).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text).stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return getWords(text)
                .stream()
                .collect(
                        Collectors.toMap(
                                (s) -> s,
                                (s) -> 1, Integer::sum));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        switch (direction) {
            case ASC: {
                return getWords(text).stream().sorted((a, b) -> a.length() - b.length()).collect(Collectors.toList());
            }
            case DESC: {
                return getWords(text).stream().sorted((a, b) -> b.length() - a.length()).collect(Collectors.toList());
            }
            default: {
                return emptyList();
            }

        }
    }
}
