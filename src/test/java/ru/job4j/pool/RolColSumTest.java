package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

class RolColSumTest {

    @Test
    void whenSumThenRowAndColAreCorrect() {
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        assertThat(sums[0].getRowSum()).isEqualTo(3);
        assertThat(sums[0].getColSum()).isEqualTo(4);
        assertThat(sums[1].getRowSum()).isEqualTo(7);
        assertThat(sums[1].getColSum()).isEqualTo(6);
    }

    @Test
    void whenAsyncSumThenSameAsSequential() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] sequential = RolColSum.sum(matrix);
        RolColSum.Sums[] async = RolColSum.asyncSum(matrix);
        for (int i = 0; i < matrix.length; i++) {
            assertThat(async[i].getRowSum()).isEqualTo(sequential[i].getRowSum());
            assertThat(async[i].getColSum()).isEqualTo(sequential[i].getColSum());
        }
    }

    @Test
    void whenAsyncSumThenRowAndColAreCorrect() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] sums = RolColSum.asyncSum(matrix);
        assertThat(sums[0].getRowSum()).isEqualTo(6);
        assertThat(sums[0].getColSum()).isEqualTo(12);
        assertThat(sums[1].getRowSum()).isEqualTo(15);
        assertThat(sums[1].getColSum()).isEqualTo(15);
        assertThat(sums[2].getRowSum()).isEqualTo(24);
        assertThat(sums[2].getColSum()).isEqualTo(18);
    }
}
