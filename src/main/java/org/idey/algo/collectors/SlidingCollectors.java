package org.idey.algo.collectors;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static java.lang.Math.max;
import static java.util.stream.Collectors.toList;

public class SlidingCollectors<T> implements Collector<T, List<List<T>>, List<List<T>>> {
    private final int size;
    private final int step;
    private final int window;

    private final Deque<T> buffer = new LinkedList<>();
    private int totalIn = 0;

    private SlidingCollectors(int size, int step) {
        this.size = size;
        this.step = step;
        this.window = max(size, step);
    }

    @Override
    public Supplier<List<List<T>>> supplier() {
        System.out.println("supplier");
        return LinkedList::new;
    }

    @Override
    public BiConsumer<List<List<T>>, T> accumulator() {
        System.out.println("accumulator");

        return (lists, t) -> {
            buffer.offer(t);
            ++totalIn;
            if (buffer.size() == window) {
                dumpCurrent(lists);
                shiftBy(step);
            }
        };
    }

    @Override
    public Function<List<List<T>>, List<List<T>>> finisher() {
        System.out.println("finisher");

        return lists -> {
            if (!buffer.isEmpty()) {
                final int totalOut = estimateTotalOut();
                if (totalOut > lists.size()) {
                    dumpCurrent(lists);
                }
            }
            return lists;
        };
    }

    private int estimateTotalOut() {
        System.out.println("estimateTotalOut");

        return max(0, (totalIn + step - size - 1) / step) + 1;
    }

    private void dumpCurrent(List<List<T>> lists) {
        System.out.println("dumpCurrent");

        final List<T> batch = buffer.stream().limit(size).collect(toList());
        lists.add(batch);
    }

    private void shiftBy(int by) {
        System.out.println("shiftBy");

        for (int i = 0; i < by; i++) {
            buffer.remove();
        }
    }

    @Override
    public BinaryOperator<List<List<T>>> combiner() {
        System.out.println("combiner");

        return (l1, l2) -> {
            throw new UnsupportedOperationException("Combining not possible");
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics");

        return EnumSet.noneOf(Characteristics.class);
    }

    public static <T> Collector<T, ?, List<List<T>>> sliding(int size) {
        return new SlidingCollectors<>(size, 1);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        Stream<Integer> s = list.stream();
        s.collect(new SlidingCollectors<>(3,2)).forEach(System.out::println);
    }
}
