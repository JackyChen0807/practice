package com.huawei.leetcode;

import com.huawei.model.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RangeMerge {
    public List<Interval> merge(int[][] intervals) {
        List<Interval> intervalList = Arrays.stream(intervals)
                .map(ints -> new Interval(ints[0], ints[1]))
                .collect(Collectors.toList());
        intervalList.sort(Comparator.comparingInt(value -> value.start));
        LinkedList<Interval> res = new LinkedList<>();

        for (Interval interval : intervalList) {
            if (res.isEmpty() || res.peekLast().end < interval.start) {
                res.add(interval);
            } else {
                res.peekLast().end = Math.max(res.peekLast().end, interval.end);
            }
        }
        int[][] intsRet = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            intsRet[i][0] = res.get(i).start;
            intsRet[i][1] = res.get(i).end;
        }
        return res;
    }
}
