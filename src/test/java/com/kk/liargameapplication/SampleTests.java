package com.kk.liargameapplication;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SampleTests {

    @Test
    void pass() {
        String a = "hi";
        String b = "hi";
        assertThat(a).isEqualTo(b);
    }
    @Test
    void failTest() {
        String a = "hi";
        String b = "hh";
        assertThat(a).isEqualTo(b);
    }
}
