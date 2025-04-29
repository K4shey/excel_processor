package com.example.excelprocessor.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickSelect {

    public static int findNthSmallest(List<Integer> numbers, int n) {

        List<Integer> copyList = new ArrayList<>(numbers);
        return quickSelect(copyList, 0, copyList.size() - 1, n - 1);
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