package vn.vti;

import org.junit.jupiter.api.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    static final Logger logger = LogManager.getLogger(CalculatorTest.class);
    static Calculator calculator;

    @BeforeAll
    // Run before any test case (Run before 1st test case)
    // Run only once in a class
    static void beforeAll() {
        calculator = new Calculator();
        logger.debug("Initialize Calculator object {}", calculator);

    }

    @BeforeAll
    static void beforeAll_1() {
        logger.info("2nd precondition");
    }

    @AfterAll
// Run after last test case
// Run only once
    static void afterAll() {
        calculator = null;
        logger.debug("Remove Calculator object {}", calculator);
    }

    @AfterAll
// Run after last test case
// Run only once
    static void afterAll_1() {
        logger.info("2nd post condition");
    }

    @BeforeEach
// Run before e
    void beforeEach(TestInfo testInfo) {
        logger.info("Start testing case {}", testInfo.getTestMethod());
    }

    @AfterEach
// Run after every test case
    void afterEach(TestInfo testInfo) {
        logger.info("FINISH TESTING CASE {}", testInfo.getTestMethod());
    }

    @Test
    void testAdd() {
        //Assert result == 2
        Assertions.assertEquals(2, calculator.add(0, 2));
    }

    @Test
    void testMinus() {
        //Assert result == 2
        Assertions.assertEquals(-2, calculator.minus(0, 2));
    }


    @ParameterizedTest
    @NullSource // Add extra null data
    @EmptySource // Add extra empty data
    @ValueSource(strings = {"-2", "0", "6", "8", "10"})
    void testAddFunctionWithZeroUsingString(String arg) {
        int result = calculator.add(0, Integer.parseInt(arg));
        Assertions.assertEquals(Integer.parseInt(arg), result);
        logger.info("Add function works correctly when adding 0 with {}. Result is: {}", arg, result);
    }

    @ParameterizedTest
    @ValueSource(classes = {Calculator.class})
    void testAddFunctionWithZero_1(Class<?> arg) {
        int result = calculator.add(0, 2);
        Assertions.assertEquals(arg, result);
        logger.info("Add function works correctly when adding 0 with {}. Result is: {}", arg, result);
    }

    //### Ý nghĩa của đoạn code:
    //* **`@ParameterizedTest` kết hợp `@ValueSource(ints = {-2, 0, 6})`:** Cho phép hàm test chạy tự động **3 lần** tương ứng với từng giá trị được truyền vào biến `arg` lần lượt là `-2`, `0`, và `6`[cite: 13].
    //* **`testAddFunctionWithZero`:** Kiểm thử tính chất phép cộng với số `0` của phương thức `add(0, arg)` xem kết quả trả về (`result`) có luôn bằng chính giá trị `arg` đó hay không (`assertEquals(arg, result)`)[cite: 13].
    //* **`logger.info(...)`:** In thông báo kết quả kiểm thử sau mỗi lần chạy kèm giá trị đối số và kết quả tương ứng[cite: 13].

    @ParameterizedTest
    @EnumSource(names = {"ADD", "MULTI", "MINUS", "DIVINE"})
    public void testEnumSource(MathOperator arg) {
        Assertions.assertTrue(arg == MathOperator.ADD || arg == MathOperator.MINUS);
    }

    @ParameterizedTest
    //Best used for key-value pair
    @CsvSource(value = {
            "fruit name | discount price | fixed price",
            "apple      | 1$             | 3$",
            "orange     | 2$             | 5$",
            "cucumber   | 1$             | 6$"
    }, useHeadersInDisplayName = true, ignoreLeadingAndTrailingWhitespace = false, delimiter = '|')
    void testCsvSource(String fruit, int discountPrice, int fixedPrice) {
        assertFalse(fruit.isEmpty());
        //Validate sell price is not negative (fixed - discount)
        Assertions.assertTrue(calculator.minus(fixedPrice, discountPrice) >= 0);
    }

    @ParameterizedTest
    //Best used for key-value pair
    @CsvSource({
            "apple, 1$, 3$",
            "orange, 2$, 5$",
            "cucumber, 1$, 6$"
    })
    void testCsvSource_1(String fruit, String discountPrice, String fixedPrice) {
        assertFalse(fruit.isEmpty());
        //Validate sell price is not negative (fixed - discount)
        int fixed = Integer.parseInt(fixedPrice.replaceAll("\\$", ""));
        int discount = Integer.parseInt(discountPrice.replaceAll("\\$", ""));
        Assertions.assertTrue(calculator.minus(fixed, discount) >= 0);
    }

    @ParameterizedTest
    //Best used for key-value pair
    @CsvFileSource(files = {"src/test/resources/fruit_price.csv"}
            , numLinesToSkip = 1)
    void testCsvSource_2(String fruit, int discountPrice, int fixedPrice) {
        assertFalse(fruit.isEmpty());
        //Validate sell price is not negative (fixed - discount)
        Assertions.assertTrue(calculator.minus(fixedPrice, discountPrice) >= 0);
    }

    @ParameterizedTest
    // Best used for key-value pair
    @CsvFileSource(resources = "/fruit_1.csv", useHeadersInDisplayName = true)
    void testCsvFileSource(String fruit, int discountPrice, int fixedPrice) {
        assertFalse(fruit.isEmpty());
        // Validate sell price is not negative (fixed - discount)
        Assertions.assertTrue(calculator.minus(fixedPrice, discountPrice) >= 0);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/fruit_1.csv", numLinesToSkip = 1)
    void testWithCsvFileSourceFromFile(String country, int reference, int a) {
        assertNotNull(country);
        assertNotEquals(0, reference);
    }

    @ParameterizedTest
    //call external medthod source; package.className#methodName
    @MethodSource(value = "vn.vti.DataProviderMethod#students")
    void testWithMethodSource(String student) {
        assertFalse(!student.isEmpty());
    }

    @ParameterizedTest
    //call internal: innject methodName
    @MethodSource(value = "students")
    void testWithMethodSourceInternal(String student) {
        assertFalse(!student.isEmpty());
    }

    static List<String> students() {
        return List.of("Long","Kim","Bang");
    }
}