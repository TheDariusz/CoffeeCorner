package com.thedariusz.coffeecorner.products;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class JuiceTest {

    final Juice orangeJuice = Juice.orange();

    @Test
    void shouldReturnJuiceWithTypeOrange() {

        Assertions.assertThat(orangeJuice.getType()).isEqualTo("Orange");

    }

}