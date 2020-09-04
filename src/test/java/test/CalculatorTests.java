package test;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;
@Listeners(TestListener.class)
public class CalculatorTests {
    Calculator calculator = new Calculator();

    @DataProvider(name = "ЗНАЧЕНИЯ ДЛЯ КАЛЬКУЛЯТОРА.")
    public Object[][] valuesForCalculator() {
        return new Object[][]{
                {50, 2},
                {0, 0},
                {-1, -1},
                {1, -1},
                {1, 0}

        };
    }


    @BeforeMethod
    public void setUp() {
        calculator = new Calculator();
    }


    @Test(description = "ТЕСТ ЗНАЧЕНИЙ ДЛЯ МЕТОДА sum.", dataProvider = "ЗНАЧЕНИЯ ДЛЯ КАЛЬКУЛЯТОРА.", groups = {"smoke"})
    public void sumTest(int firstValue, int secondValue) {
        int result = calculator.sum(firstValue, secondValue);
        Assert.assertEquals(result, firstValue + secondValue);
    }


    @Test(description = "ТЕСТ ЗНАЧЕНИЙ ДЛЯ МЕТОДА differance.", dataProvider = "ЗНАЧЕНИЯ ДЛЯ КАЛЬКУЛЯТОРА.", groups = {"smoke"})
    public void differenceTest(int firstValue, int secondValue) {
        int result = calculator.difference(firstValue, secondValue);
        Assert.assertEquals(result, firstValue - secondValue);


    }

    @Test(description = "ТЕСТ ЗНАЧЕНИЙ ДЛЯ МЕТОДА divide.", groups = {"деление"})
    public void divideTest() {
        int result = calculator.divide(2, 1);
        int result2 = calculator.divide(-2, -1);
        Assert.assertEquals(result, 2);
        Assert.assertEquals(result2, 2);
    }

    @Test(description = "ТЕСТ ЗНАЧЕНИЙ ДЛЯ МЕТОДА multiply.", dataProvider = "ЗНАЧЕНИЯ ДЛЯ КАЛЬКУЛЯТОРА.", groups = {"smoke"})
    public void multiplyTest(int firstValue, int secondValue) {
        int result = calculator.multiply(firstValue, secondValue);
        Assert.assertEquals(result, firstValue * secondValue);
    }


    @Test(description = "ТЕСТ ДЕЛЕНИЯ НА 0", expectedExceptions = ArithmeticException.class, groups = {"smoke"})
    public void nullDivideTest() {
        int result = calculator.divide(0, 0);
        Assert.assertEquals(result, 0 / 0);
    }

    @Test(description = "ТЕСТ НА ДЕЛЕНИЕ C ДРОБНЫМ ОСТАТКОМ", groups = {"деление"})
    public void divideTest2() {
        int result = calculator.divide(50, 26);
        Assert.assertEquals(result, 1);
    }

    @Test(description = "УМОЖНОЖЕНИЕ НА 0", groups = {"smoke"})
    public void nullMultiplyTes() {
        int result = calculator.multiply(0, 0);
        Assert.assertEquals(result, 0);
    }

    @Test(description = "ПРЕВЫШЕНИЕ ВОЗМОЖНОСТЕЙ КАЛЬКУЛЯТОРА", groups = {"smoke"},priority = 1)
    public void maxValueCalculator() {
        int max = 999999999;
        int result = calculator.sum(max, max);
        if (result < 0) {
            Assert.fail();
        }
    }

    @Test(description = "ТЕСТ СУММЫ И УМНОЖЕНИЯ", groups = {"smoke"})
    public void sumAndMultiplyTest() {
        int result = calculator.sum(2, 2) * calculator.multiply(2, 2);
        Assert.assertEquals(result, 16);
    }

    @Test(description = "ТЕСТ retryAnalyzer", groups = {"smoke"}, retryAnalyzer = Retry.class,invocationCount = 50,threadPoolSize = 10)
    public void randomTest() {
        Random r = new Random();
        int result = calculator.multiply(1, r.nextInt(2));
        Assert.assertEquals(result, 1);
    }


    @AfterMethod(alwaysRun = true)
    public void description() {
        System.out.println("ВСЕ ¯\\_(ツ)_/¯");

    }
}


