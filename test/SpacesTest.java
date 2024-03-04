import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpacesTest {

    @Test
    void check0() {
        assertEquals(0, Spaces.getPressCount(0));
    }

    @Test
    void check2() {
        assertEquals(2, Spaces.getPressCount(2));
    }

    @Test
    void check3() {
        assertEquals(2, Spaces.getPressCount(3));
    }

    @Test
    void check4() {
        assertEquals(1, Spaces.getPressCount(4));
    }

    @Test
    void check6() {
        assertEquals(3, Spaces.getPressCount(6));
    }

    @Test
    void check7() {
        assertEquals(3, Spaces.getPressCount(7));
    }

    @Test
    void check9() {
        assertEquals(3, Spaces.getPressCount(9));
    }

    @Test
    void check11() {
        assertEquals(4, Spaces.getPressCount(11));
    }

    @Test
    void check12() {
        assertEquals(3, Spaces.getPressCount(12));
    }

    @Test
    void check10e9() {
        assertEquals(250000000, Spaces.getPressCount(1_000_000_000));
    }
}