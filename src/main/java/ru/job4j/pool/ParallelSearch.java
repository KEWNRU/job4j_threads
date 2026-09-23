package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T target;
    private final int from;
    private final int to;

    public ParallelSearch(T[] array, T target, int from, int to) {
        this.array = array;
        this.target = target;
        this.from = from;
        this.to = to;
    }


    @Override
    protected Integer compute() {
        if (to - from + 1 <= 10) {
            for (int i = from; i <= to; i++) {
                if (array[i].equals(target)) {
                    return i;
                }
            }
            return -1;
        } else {
            int middle = (from + to) / 2;

            ParallelSearch<T> leftSearch = new ParallelSearch<>(array, target, from, middle);
            ParallelSearch<T> rightSearch = new ParallelSearch<>(array, target, middle + 1, to);

            leftSearch.fork();
            rightSearch.fork();

            Integer leftRsl = leftSearch.join();
            Integer rightRsl = rightSearch.join();
            return leftRsl != -1 ? leftRsl : rightRsl;
        }
    }
    public static <T> int search(T[] array, T target) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelSearch<>(array, target, 0, array.length - 1));
    }
}
