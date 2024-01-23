package fp;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.*;
import java.util.List;

class ExerciseNotCompletedException extends RuntimeException {
    public ExerciseNotCompletedException() {
        super("Exercise is not completed yet");
    }
}

/**
 * {@link HW} is an exercise class. Each method returns a functional interface and it should be implemented
 * using either lambda or a method reference. Every method that is not implemented yet throws
 * {@link ExerciseNotCompletedException}.
 * <p>
 * TODO: remove exception and implement each method of this class using lambda or method reference
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 */

record pair<U,V> (U u, V v) {}
public class HW {

    static <U,V> List<pair<U,V>> zip(List<U> l1, List<V> l2) {
        // walk through the U's and V's
        // construct list of pairs
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = 0; i < l1.size(); i++) {
            l.add(i);
        }
        return map(l, i -> new pair<>(l1.get(i), l2.get(i)));
    }

    static <U,V> List<V> map(Iterable<U> l, Function<U,V> f) {
        // walk through the U's
        // use f at every stage f.apply
        // construct list of V's
        List<V> emptyList = new ArrayList<>();
        for (U x: l) {
            V mapValue = f.apply(x);
            emptyList.add(mapValue);
        }
        return emptyList;
    }

    static <U,V> List<V> flatmap(Iterable<U> l, Function<U,List<V>> f) {
        // walk through the U's
        // use f at every stage f.apply
        // construct list of V's
        List<V> emptyList = new ArrayList<>();
        for (U x: l) {
            List<V> mapValue = f.apply(x);
            emptyList.addAll(mapValue);
        }
        return emptyList;
    }
    
    
    static <U,V> V foldLeft(V e, Iterable<U>l, BiFunction<V,U,V> f){
        // walk through the U's [u1,u2, ..,un]
        //                       e
        // use f at every stage v1= f.apply(e,u1)
        //                         v2= f.apply(v1,u2)
        //						    v3= f.apply(v2,u3)..
        // return the last v
        V accumulatedValue = e;
        for (U x: l) {
            accumulatedValue = f.apply(accumulatedValue, x);
        }
        return accumulatedValue;
    }
    
    
    // similar to above
    // but from the right
    //     vn=  f(un,e)
    //          vn-1 = f(un-1,vn)
    // ..
    // return the first v
    static <U,V> V foldRight(V e, List<U>l, BiFunction<U,V,V> f){
        V accumulatedValue = e;
        for (int i = l.size()-1; i >= 0; i--) {
            accumulatedValue = f.apply(l.get(i), accumulatedValue);
        }
        return accumulatedValue;
    }
    
    static <U> boolean exists(Iterable<U> l, Predicate<U> p){
        for (U x: l) {
            if (p.test(x)) return true;
        }
        return false;
    }

    static <U> Iterable<U> filter(Iterable<U> l, Predicate<U> p){
        List<U> list = new ArrayList<>();
        for (U x: l) {
            if (p.test(x)) list.add(x);
        }

    return list;
    }

    static record Person(String name, int age, boolean teach) {

        public String name() {
            return name;
        }

        public int age() {
            return age;
        }

        public boolean teach() {
            return teach;
        }

    }

    public static void printFiltered2(ArrayList<Person> l, Predicate<Person> f) {
        Iterable<Person> l2 = filter(l,f);
        map(l2, (Person p) -> {return p.name(); });

    }
    
    static <U> U minVal(Iterable<U> l, Comparator<U> c){
        // write using fold.  No other loops or recursion permitted. 
        Optional<U> ans = foldLeft(Optional.empty(),l, (min,curr)->{
            if(!min.isPresent() || c.compare(curr, min.get())<0){
                return Optional.of(curr);
            }
            else{
                return min;
            }
        });
        return ans.orElse(null);
    }
    
    /**
     * Returns {@link Supplier} that always supply "Hello"
     *
     * @return a string supplier
     */
    public static Supplier<String> helloSupplier() {
        return new Supplier<String>() {
            @Override
            public String get() {
                return "Hello";
            }
        };

    
    }

    /**
     * Returns a {@link Predicate} of string that checks if string is empty
     *
     * @return a string predicate
     */
    public static Predicate<String> isEmptyPredicate() {
        return new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty() || s==null;
            }
        };
    }

    /**
     * Return a {@link Function} that accepts {@link String} and returns that string repeated n time, where n is passed
     * as function argument
     *
     * @return function that repeats Strings
     */
    public static BiFunction<String, Integer, String> stringMultiplier() {
        return (str , n) -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(str);
            }
            return sb.toString();
        };
    }

    /**
     * Returns a {@link Function} that converts a {@link BigDecimal} number into a {@link String} that start with
     * a dollar sign and then gets a value
     *
     * @return function that converts adds dollar sign
     */
    public static Function<BigDecimal, String> toDollarStringFunction() {
        return number -> "$" + number.toString();
    }

    /**
     * Receives two parameter that represent a range and returns a {@link Predicate<String>} that verifies if string
     * length is in the specified range. E.g. min <= length < max
     *
     * @param min min length
     * @param max max length
     * @return a string predicate
     */
    public static Predicate<String> lengthInRangePredicate(int min, int max) {
        return str -> str.length() >= min && str.length() < max;
    }

    /**
     * Returns a {@link Supplier} of random integers
     *
     * @return int supplier
     */
    public static IntSupplier randomIntSupplier() {
        Random random = new Random();
        return random::nextInt;
    }


    /**
     * Returns an {@link IntUnaryOperator} that receives an int as a bound parameter, and returns a random int
     *
     * @return int operation
     */
    public static IntUnaryOperator boundedRandomIntSupplier() {
        Random random = new Random();
        return unit -> random.nextInt(unit);
    }

    /**
     * Returns {@link IntUnaryOperator} that calculates an integer square
     *
     * @return square operation
     */
    public static IntUnaryOperator intSquareOperation() {
        return x -> x * x;
    }

    /**
     * Returns a {@link LongBinaryOperator} sum operation.
     *
     * @return binary sum operation
     */
    public static LongBinaryOperator longSumOperation() {
        return (x, y) -> x + y;
    }

    /**
     * Returns a {@link ToIntFunction<String>} that converts string to integer.
     *
     * @return string to int converter
     */
    public static ToIntFunction<String> stringToIntConverter() {
        return Integer::parseInt;
    }

    /**
     * Receives int parameter n, and returns a {@link Supplier} that supplies {@link IntUnaryOperator}
     * that is a function f(x) = n * x
     *
     * @param n a multiplier
     * @return a function supplier
     */
    public static Supplier<IntUnaryOperator> nMultiplyFunctionSupplier(int n) {
        return () -> x -> n * x;
    }

    /**
     * Returns a {@link UnaryOperator} that accepts str to str function and returns the same function composed with trim
     *
     * @return function that composes functions with trim() function
     */
    public static UnaryOperator<Function<String, String>> composeWithTrimFunction() {
        return new UnaryOperator<Function<String,String>>() {
            public Function<String,String> apply(Function<String,String> f){
                return new Function<String,String>(){
                    public String apply(String s){
                        String trim = s.trim();
                        return f.apply(trim);
                    }
                };
            }
        };
    }


    /**



    /**
     * Returns a {@link BiFunction} that has two parameters. First is {@link IntUnaryOperator} which is some integer function.
     * Second is {@link IntPredicate} which is some integer condition. And the third is {@link IntUnaryOperator} which is
     * a new composed function that uses provided predicate (second parameter of binary function) to verify its input
     * parameter. If predicate returns {@code true} it applies a provided integer function
     * (first parameter of binary function) and returns a result value, otherwise it returns an element itself.
     *
     * @return a binary function that receiver predicate and function and compose them to create a new function
     */
    public static BiFunction<IntUnaryOperator, IntPredicate, IntUnaryOperator> functionToConditionalFunction() {
        return (function, predicate) -> {
            return input -> predicate.test(input) ? function.applyAsInt(input) : input;
        };
    }

    /**
     * Returns a {@link BiFunction} which first parameter is a {@link Map} where key is a function name, and value is some
     * {@link IntUnaryOperator}, and second parameter is a {@link String} which is a function name. If the map contains a
     * function by a given name then it is returned by high order function otherwise an identity() is returned.
     *
     * @return a high-order function that fetches a function from a function map by a given name or returns identity()
     */
    public static BiFunction<Map<String, IntUnaryOperator>, String, IntUnaryOperator> functionLoader() {
        return (map, name) -> map.getOrDefault(name, IntUnaryOperator.identity());
    }

    /**
     * Returns a comparator of type T that is comparing values extracted using the provided mapper function.
     * <p>
     * E.g. imagine you need to compare accounts by their balance values.
     * <pre>{@code
     * Comparator<Account> balanceComparator = comparing(Account::getBalance);
     * }</pre>
     * <p>
     * PLEASE NOTE, that @{@link Comparator} is a functional interface, and you should manually write a lambda expression
     * to implement it.
     *
     * @param mapper a mapper function that allows to map an object to a comparable value
     * @return a comparator instance
     */
    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> mapper) {
        return (comp1, comp2) -> {
            U u1 = mapper.apply(comp1);
            U u2 = mapper.apply(comp2);
            return u1.compareTo(u2);
        };
        
    }

    /**
     * Returns a comparator of type T that uses a provided comparator to compare objects, and only if they are equal
     * it's comparing values extracted using the provided mapper function.
     * <p>
     * E.g. suppose you want to compare accounts by balance, but in case two people have the same balance you want to
     * compare their first names:
     * <pre>{@code
     * Comparator<Account> accountComparator = thenComparing(balanceComparator, Account::getFirstName);
     * }</pre>
     * <p>
     *
     * @param comparator an initial comparator
     * @param mapper     a mapper function that is used to extract values when initial comparator returns zero
     * @return a comparator instance
     */
    public static <T, U extends Comparable<? super U>> Comparator<T> thenComparing(
            Comparator<? super T> comparator, Function<? super T, ? extends U> mapper) {
        return (comp1, comp2) -> {
            int compare = comparator.compare(comp1, comp2);
            if (compare == 0) {
                U u1 = mapper.apply(comp1);
                U u2 = mapper.apply(comp2);
                return u1.compareTo(u2);
            }
            return compare;
        };
    }

    /**
     * Returns {@link Supplier} of {@link Supplier} of {@link Supplier} of {@link String} "WELL DONE!".
     *
     * @return a supplier instance
     */
    public static Supplier<Supplier<Supplier<String>>> trickyWellDoneSupplier() {
        Supplier<String> supplier = () -> "WELL DONE!";
        Supplier<Supplier<String>> middleSupplier = () -> supplier;
        return () -> middleSupplier;
    }

    public static <T> List<T> myRepl(int n, T v){
        return Collections.nCopies(n, v);
    }

    public static int sumOdd(List<Integer> values){
        BiFunction<Integer, Integer, Integer> sum = (val, num ) -> val + num;
        return foldLeft(0, filter(values, num -> num % 2 ==1), sum);
    }

    public static <T> List<T> repl(List<T> xs, int n){
        return xs.stream().flatMap(x -> Collections.nCopies(n, x).stream()).toList();
    }

    public static int totalLength(List<String> xs){
        return xs.stream().filter(s -> s.startsWith("A")).mapToInt(String::length).reduce(0, Integer::sum);
    }

    public static List<String> titlecase(List<String> xs){
        List<String> result = new ArrayList<>();
        for (String s: xs) {
            if (s == null || s.isEmpty()) {
                result.add(s);
                continue;
            } 
            if (Character.toLowerCase(s.charAt(0)) == 'a') {
                result.add(Character.toUpperCase(s.charAt(0)) + s.substring(1));
            }else{
                result.add(s);
            }
    }
    return result;
    }

    public static int countVowelsPali(List<String> xs){
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (String word : xs) {
            if (word == null || word.isEmpty()) {
                continue;
            }
            if (isPalindrome(word)) {
                for(char c : word.toCharArray()){
                    if(vowels.indexOf(c) != -1){
                        count++;
                    }
                }
                
            }
        
        }
        return count;

    }

    private static boolean isPalindrome(String word) {
        int i = 0, j = word.length() - 1;
        while (i < j) {
            if (word.charAt(i) != word.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;

    }

    public static void main(String[] args){
        ArrayList<String> l = new ArrayList<>();
        l.add("a");
        l.add("bc");
        l.add("dcf");
        System.out.println(map(l, (String x) -> { return x.length();}));
        // U = String, V = Integer [1,2,3]

    }

}
