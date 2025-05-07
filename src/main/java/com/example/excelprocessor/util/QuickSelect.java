package com.example.excelprocessor.util;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public final class QuickSelect {

    private static final Random random = new Random();

    public static int findNthSmallest(List<Integer> numbers, int n) {

        validateNumbersNotEmpty(numbers);

        if (n < 1 || n > numbers.size()) {
            throw new IllegalArgumentException("Некорректный порядковый номер N");
        }

        return quickSelect(numbers, 0, numbers.size() - 1, n - 1);
    }

    private static void validateNumbersNotEmpty(List<Integer> numbers) {

        if (Objects.isNull(numbers) || numbers.isEmpty()) {
            throw new IllegalArgumentException("Список не может быть пустым");
        }
    }

    private static int quickSelect(List<Integer> numbers, int from, int to, int n) {

        if (from == to) {
            return numbers.get(from);
        }

        int pivotIdx = partition(numbers, from, to);

        if (n == pivotIdx) {
            return numbers.get(n);
        } else if (n < pivotIdx) {
            return quickSelect(numbers, from, pivotIdx - 1, n);
        } else {
            return quickSelect(numbers, pivotIdx + 1, to, n);
        }
    }

    private static int partition(List<Integer> list, int from, int to) {

        int pivotIdx = from + random.nextInt(to - from + 1);
        Collections.swap(list, pivotIdx, to);
        int currIdx = from;
        int pivot = list.get(to);

        for (int j = from; j < to; j++) {
            if (list.get(j) < pivot) {
                Collections.swap(list, j, currIdx);
                currIdx++;
            }
        }
        Collections.swap(list, currIdx, to);

        return currIdx;
    }
}