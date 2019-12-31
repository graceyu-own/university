package org.techas.falleaves.utils;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void test1() {

        double init = 100;
        double value = 0.1;

        for(int i = 0; i < 365; i++) {
            init *= (1 + value);
        }

        System.out.printf("%-30f", init);

    }

}
