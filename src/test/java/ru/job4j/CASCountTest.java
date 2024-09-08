package ru.job4j;


import org.junit.jupiter.api.Test;

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
}
