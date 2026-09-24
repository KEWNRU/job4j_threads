package ru.job4j.pool;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParallelSearchTest {

    /**
     * Маленький массив (≤10) — должен идти линейный поиск.
     * Проверяем, что нужный элемент находится по своему индексу.
     */
    @Test
    void whenSmallArrayAndElementFoundThenReturnIndex() {
        Integer[] array = {1, 2, 3, 4, 5};
        int result = ParallelSearch.search(array, 3);
        assertThat(result).isEqualTo(2);
    }

    /**
     * Маленький массив, элемента нет — ждём -1.
     */
    @Test
    void whenSmallArrayAndElementNotFoundThenMinusOne() {
        Integer[] array = {1, 2, 3, 4, 5};
        int result = ParallelSearch.search(array, 99);
        assertThat(result).isEqualTo(-1);
    }

    /**
     * Большой массив (>10) — идёт рекурсия.
     * 100 элементов, ищем тот, что в середине.
     */
    @Test
    void whenBigArrayAndElementFoundThenReturnIndex() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        int result = ParallelSearch.search(array, 57);
        assertThat(result).isEqualTo(57);
    }

    /**
     * Большой массив, элемента нет — ждём -1.
     */
    @Test
    void whenBigArrayAndElementNotFoundThenMinusOne() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        int result = ParallelSearch.search(array, 1000);
        assertThat(result).isEqualTo(-1);
    }

    /**
     * Другой тип данных — строки.
     * Проверяем, что дженерик работает с любым типом.
     */
    @Test
    void whenStringArrayThenFindByName() {
        String[] array = {"Ivan", "Petr", "Sidor", "Anna", "Oleg"};
        int result = ParallelSearch.search(array, "Sidor");
        assertThat(result).isEqualTo(2);
    }

    /**
     * Строковый массив, элемента нет.
     */
    @Test
    void whenStringArrayAndElementNotFoundThenMinusOne() {
        String[] array = {"Ivan", "Petr", "Sidor"};
        int result = ParallelSearch.search(array, "Unknown");
        assertThat(result).isEqualTo(-1);
    }

    /**
     * Краевой случай — элемент в самом начале.
     */
    @Test
    void whenElementAtStartThenZero() {
        String[] array = {"Ivan", "Petr", "Sidor", "Anna", "Oleg",
                "Masha", "Dasha", "Pasha", "Sasha", "Gena", "Vera"};
        int result = ParallelSearch.search(array, "Ivan");
        assertThat(result).isEqualTo(0);
    }

    /**
     * Краевой случай — элемент в самом конце.
     */
    @Test
    void whenElementAtEndThenLastIndex() {
        String[] array = {"Ivan", "Petr", "Sidor", "Anna", "Oleg",
                "Masha", "Dasha", "Pasha", "Sasha", "Gena", "Vera"};
        int result = ParallelSearch.search(array, "Vera");
        assertThat(result).isEqualTo(10);
    }
}