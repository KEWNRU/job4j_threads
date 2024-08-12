package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void offer(T value) {
        while (capacity <= queue.size()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.notifyAll();
        queue.offer(value);
    }


    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        T value = queue.poll();
        this.notifyAll();
        return value;
    }
}