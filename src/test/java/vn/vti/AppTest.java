package vn.vti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;



public class AppTest {

    @Test
    public void shouldBeItselfWhenAddZero() {
        int result = new App().add(0, 8);
        assertEquals(8, result);
    }

    @Test
    public void shouldBeNegative() {
        int result = new App().add(-2, -3);
        assertEquals(-5, result);
    }

} // <-- RẤT QUAN TRỌNG: Thêm dấu ngoặc nhọn này để đóng class AppTest