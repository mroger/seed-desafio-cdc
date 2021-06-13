package br.com.roger.study.casadocodigo;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Assertions;

public class PropertyTest {

    @Property
    void concatenationLength(@ForAll String s1, @ForAll String s2) {
        String s3 = s1 + s2;

        Assertions.assertEquals(s1.length() + s2.length(), s3.length());
    }
}
