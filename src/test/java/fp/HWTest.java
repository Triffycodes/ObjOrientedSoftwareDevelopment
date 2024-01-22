package fp;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;



public class HWTest {


/**
 * A test class for {@link HW}.
 *
 */
    @Test
    void helloSupplier() {
        Supplier<String> helloSupplier = HW.helloSupplier();

        assertEquals("Hello", helloSupplier.get());
    }


    @Test
   
    void isEmptyPredicate() {
        Predicate<String> isEmptyPredicate = HW.isEmptyPredicate();

        boolean nonEmptyStringResult = isEmptyPredicate.test("fasdfa");
        boolean emptyStringResult = isEmptyPredicate.test("");

        assertFalse(nonEmptyStringResult);
        assertTrue(emptyStringResult);
    }

    @Test
    void stringMultiplier() {
        BiFunction<String, Integer, String> stringMultiplier = HW.stringMultiplier();

        String threeTimesHi = stringMultiplier.apply("Hi", 3);
        String twoTimesHello = stringMultiplier.apply("Hello", 2);

        assertEquals("HiHiHi", threeTimesHi);
        assertEquals("HelloHello", twoTimesHello);
    }

    @Test
    void toDollarStringFunction() {
        Function<BigDecimal, String> toDollarStringFunction = HW.toDollarStringFunction();
        String tenDollarStr = toDollarStringFunction.apply(BigDecimal.TEN.setScale(2));

        assertEquals("$10.00", tenDollarStr);
    }

    @Test
    void lengthInRangePredicate() {
        Predicate<String> lengthInRangePredicate = HW.lengthInRangePredicate(4, 10);

        boolean twoLetterStringResult = lengthInRangePredicate.test("Hi");
        boolean fourLetterStringResult = lengthInRangePredicate.test("Hola");
        boolean fiveLetterStringResult = lengthInRangePredicate.test("Amigo");
        boolean eightLetterStringResult = lengthInRangePredicate.test("Lalaland");
        boolean thirteenLetterStringResult = lengthInRangePredicate.test("Lambda rocks!");

        assertFalse(twoLetterStringResult);
        assertTrue(fourLetterStringResult);
        assertTrue(fiveLetterStringResult);
        assertTrue(eightLetterStringResult);
        assertFalse(thirteenLetterStringResult);
    }

    @Test
    void randomIntSupplier() {
        IntSupplier randomIntSupplier = HW.randomIntSupplier();

        int firstValue = randomIntSupplier.getAsInt();
        int secondValue = randomIntSupplier.getAsInt();

        assertNotEquals(firstValue, secondValue);
    }

    @Test
    void boundedRandomIntSupplier() {
        IntUnaryOperator boundedRandomIntSupplier = HW.boundedRandomIntSupplier();

        int randomIntLessThan10 = boundedRandomIntSupplier.applyAsInt(10);
        int randomIntLessThan100 = boundedRandomIntSupplier.applyAsInt(100);
        int randomIntLessThan1000 = boundedRandomIntSupplier.applyAsInt(1000);
        int randomIntLessThan10000 = boundedRandomIntSupplier.applyAsInt(1000);
        int randomIntLessThan100000 = boundedRandomIntSupplier.applyAsInt(1000);

        assertTrue(randomIntLessThan10 < 10);
        assertTrue(randomIntLessThan100 < 100);
        assertTrue(randomIntLessThan1000 < 1000);
        assertTrue(randomIntLessThan10000 < 10000);
        assertTrue(randomIntLessThan100000 < 100000);
    }

    @Test
    void intSquareOperation() {
        IntUnaryOperator squareOperation = HW.intSquareOperation();

        int squareOfFour = squareOperation.applyAsInt(4);
        int squareOfZero = squareOperation.applyAsInt(0);

        assertEquals(16, squareOfFour);
        assertEquals(0, squareOfZero);
    }

    @Test
    void longSumOperation() {
        LongBinaryOperator sumOperation = HW.longSumOperation();


        long sumOfSevenAndEight = sumOperation.applyAsLong(7, 8);
        long sumOfTenAndZero = sumOperation.applyAsLong(10, 0);
        long sumOfFiveAndMinusTen = sumOperation.applyAsLong(5, -10);

        assertEquals(15, sumOfSevenAndEight);
        assertEquals(10, sumOfTenAndZero);
        assertEquals(-5, sumOfFiveAndMinusTen);
    }

    @Test
    void stringToIntConverter() {
        ToIntFunction<String> stringToIntConverter = HW.stringToIntConverter();

        int num = stringToIntConverter.applyAsInt("234");
        int negativeNum = stringToIntConverter.applyAsInt("-122");

        assertEquals(234, num);
        assertEquals(-122, negativeNum);
    }

    @Test
    void nMultiplyFunctionSupplier() {
        Supplier<IntUnaryOperator> fiveMultiplyFunctionSupplier = HW.nMultiplyFunctionSupplier(5);

        IntUnaryOperator multiplyByFiveOperation = fiveMultiplyFunctionSupplier.get();
        int result = multiplyByFiveOperation.applyAsInt(11); // 11 * 5 = 55

        assertEquals(55, result);
    }

    @Test
    void composeWithTrimFunction() {
        UnaryOperator<Function<String, String>> composeWithTrimFunction = HW.composeWithTrimFunction();
        Function<String, String> toLowerWithTrim = composeWithTrimFunction.apply(String::toLowerCase);
        Function<String, String> threeTimesRepeatWithTrim = composeWithTrimFunction.apply(s -> s.repeat(3));

        String hey = toLowerWithTrim.apply("  Hey ");
        String threeTimesHi = threeTimesRepeatWithTrim.apply("  Hi  ");

        assertEquals("hey", hey);
        assertEquals("HiHiHi", threeTimesHi);
    }

    
    

    @Test
    void functionToConditionalFunction() {
        BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> intFunctionToConditionalIntFunction
                = HW.functionToConditionalFunction();

        IntUnaryOperator abs = intFunctionToConditionalIntFunction.apply(a -> -a, a -> a < 0);

        assertEquals(5, abs.applyAsInt(-5));
        assertEquals(0, abs.applyAsInt(0));
        assertEquals(5, abs.applyAsInt(5));
    }

    @Test
    void functionLoader() {
        BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator> functionLoader = HW.functionLoader();
        Map<String, IntUnaryOperator> functionMap = new HashMap<>();
        functionMap.put("increment", x -> x + 1);
        functionMap.put("square", x -> x * x);

        IntUnaryOperator incrementFunction = functionLoader.apply(functionMap, "increment");
        IntUnaryOperator squareFunction = functionLoader.apply(functionMap, "square");
        IntUnaryOperator identityFunction = functionLoader.apply(functionMap, "none");

        assertEquals(5, incrementFunction.applyAsInt(4));
        assertEquals(9, squareFunction.applyAsInt(3));
        assertEquals(10, identityFunction.applyAsInt(10));
    }

    @Test
    void comparing() {
        var strLengthComparator = HW.comparing(String::length);
        var stringList = new ArrayList<>(List.of("Me", "I", "All of us", "They", "She"));

        stringList.sort(strLengthComparator);

        assertEquals(stringList,List.of("I", "Me", "She", "They", "All of us"));
    }

    @Test
    void thenComparing() {
        var strLengthComparator = Comparator.comparing(String::length);

        Comparator<String> lengthThenNaturalComparator = HW.thenComparing(strLengthComparator, s -> s);
        var stringList = new ArrayList<>(List.of("Me", "I", "All of us", "They", "She", "He"));

        stringList.sort(lengthThenNaturalComparator);
        assertEquals(stringList,List.of("I", "He", "Me", "She", "They", "All of us"));
    }

    @Test
    void trickyWellDoneSupplier() {
        Supplier<Supplier<Supplier<String>>> wellDoneSupplier = HW.trickyWellDoneSupplier();

        String wellDoneStr = wellDoneSupplier.get().get().get();

        assertEquals("WELL DONE!", wellDoneStr);
    }

    @Test
    void testmyRepl(){
        List<Integer> result = HW.myRepl(5, 2);
        assertEquals(5, result.size());
        assertTrue(result.stream().allMatch(i -> i == 2));

        List<String> result2 = HW.myRepl(3, "Hi");
        assertEquals(3, result2.size());
        assertTrue(result2.stream().allMatch(i -> i.equals("Hi")));

        List<Integer> result3 = HW.myRepl(0, 2);
        assertEquals(0, result3.size());
        assertTrue(result3.stream().allMatch(i -> i == 2));

        List<String> result4 = HW.myRepl(6, "Adarsh");
        assertEquals(6, result4.size());
        assertTrue(result4.stream().allMatch(i -> i.equals("Adarsh")));
    }

    @Test
    void testsumOdd(){
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7);
        int result = HW.sumOdd(list);
        assertEquals(16, result);

        List<Integer> list2 = List.of(2, 4, 6, 8, 10);
        int result2 = HW.sumOdd(list2);
        assertEquals(0, result2);

        List<Integer> list3 = List.of(1, 3, 5, 7, 9);
        int result3 = HW.sumOdd(list3);
        assertEquals(25, result3);

        List<Integer> list4 = List.of(2, 4, 6, 8, 10, 11);
        int result4 = HW.sumOdd(list4);
        assertEquals(11, result4);
    }

    @Test
    void testrepl(){
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> repValues = HW.repl(values, 3);
        List<Integer> expected = Arrays.asList(1, 1, 1, 2, 2, 2, 3, 3, 3, 4 ,4 ,4, 5, 5, 5);
        assertEquals(expected, repValues);

        List<String> values2 = Arrays.asList("a", "b", "c");
        List<String> repValues2 = HW.repl(values2, 2);
        List<String> expected2 = Arrays.asList("a", "a", "b", "b", "c", "c");
        assertEquals(expected2, repValues2);

        List<Integer> values3 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> repValues3 = HW.repl(values3, 0);
        List<Integer> expected3 = Arrays.asList();
        assertEquals(expected3, repValues3);

        List<String> values4 = Arrays.asList("a", "b", "c");
        List<String> repValues4 = HW.repl(values4, 1);
        List<String> expected4 = Arrays.asList("a", "b", "c");
        assertEquals(expected4, repValues4);
    }

    @Test
    void testTotalLength(){
        List<String> list = Arrays.asList("apple", "Banana", "Avocado", "Grape", "Adarsh", "adarsh");
        int sum = HW.totalLength(list);
        assertEquals(13, sum);

        List<String> list2 = Arrays.asList("Java", "Python", "C++", "JavaScript");
        int sum2 = HW.totalLength(list2);
        assertEquals(0, sum2);

        List<String> list3 = Arrays.asList("Hello", "World", "GitHub", "Copilot", "Ai");
        int sum3 = HW.totalLength(list3);
        assertEquals(2, sum3);

        List<String> list4 = Arrays.asList("GitHub", "Copilot", "AI", "Programming", "Assistant");
        int sum4 = HW.totalLength(list4);
        assertEquals(11, sum4);

        List<String> list5 = Arrays.asList("java", "Angular", "python", "c++", "javascript");
        int sum5 = HW.totalLength(list5);
        assertEquals(7, sum5);

        List<String> list6 = Arrays.asList("Apple", "Ant", "ant", "Avocado", "Grape");
        int sum6 = HW.totalLength(list6);
        assertEquals(15, sum6);
    }

    @Test
    void testTitleCase(){
        List<String> list = Arrays.asList("apple", "banana", "avocado", "date", "elderberry");
        List<String> result = HW.titlecase(list);
        List<String> expected = Arrays.asList("Apple", "banana", "Avocado", "date", "elderberry");
        assertEquals(expected, result);

        List<String> list2 = Arrays.asList("java", "python", "c#", "ruby");
        List<String> result2 = HW.titlecase(list2);
        List<String> expected2 = Arrays.asList("java", "python", "c#", "ruby");
        assertEquals(expected2, result2);

        List<String> list3 = Arrays.asList("ant", "antelope", "aardvark", "alligator", "elephant");
        List<String> result3 = HW.titlecase(list3);
        List<String> expected3 = Arrays.asList("Ant", "Antelope", "Aardvark", "Alligator", "elephant");
        assertEquals(expected3, result3);

        List<String> list4 = Arrays.asList("aAaAdfg", "Aasae", "AaA", "baAaA", "aAaAaA");
        List<String> result4 = HW.titlecase(list4);
        List<String> expected4 = Arrays.asList("AAaAdfg", "Aasae", "AaA", "baAaA", "AAaAaA");
        assertEquals(expected4, result4);
    }

    @Test
    void testCountVowelPali() {
        List<String> xs = Arrays.asList("anna", "banana", "civic", "mouse");
        int result = HW.countVowelsPali(xs);
        assertEquals(4, result);

        List<String> xs2 = Arrays.asList("level", "banana", "avocado", "date", "madam");
        int result2 = HW.countVowelsPali(xs2);
        assertEquals(4, result2);

        List<String> xs3 = Arrays.asList("java", "python", "c", "ruby", "hello");
        int result3 = HW.countVowelsPali(xs3);
        assertEquals(0, result3);

        List<String> xs4 = Arrays.asList("a", "b", "c", "d", "e");
        int result4 = HW.countVowelsPali(xs4);
        assertEquals(2, result4);
    }
}
