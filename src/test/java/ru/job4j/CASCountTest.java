package ru.job4j;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CASCountTest {

    @Test
    public void whenIncrementThenCountIsIncreased() {
        CASCount casCount = new CASCount();
        casCount.increment();
        assertEquals(1, casCount.get());
    }

    @Test
    public void whenMultipleIncrementsThenCountIsCorrect() {
        CASCount casCount = new CASCount();
        casCount.increment();
        casCount.increment();
        casCount.increment();
        assertEquals(3, casCount.get());
    }

    @Test
    public void whenGetThenReturnCurrentCount() {
        CASCount casCount = new CASCount();
        assertEquals(0, casCount.get());
    }

    @Test
    public void whenTwoThreadsThenCountIsCorrect() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread first = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                casCount.increment();
            }
        });
        Thread second = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                casCount.increment();
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertEquals(200, casCount.get());
        };
    }
