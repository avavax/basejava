package com.basejava.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainStream {
    public static void main(String[] args) {

        System.out.println(minValue(new int[] {4, 3, 2, 3}));

        System.out.println(oddOrEven(Arrays.asList(4, 3, 2, 3, 7, 8)));
    }

    private static int minValue(int[] values) {
        return IntStream.of(values)
                .distinct()
                .sorted()
                .reduce(0, (acc, item) -> acc * 10 + item);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> map = integers.stream()
            .collect(Collectors.partitioningBy(item -> item % 2 == 0));
        int sum = integers.stream()
                .reduce(0, Integer::sum);
        return map.get(sum % 2 != 0);
    }
}
