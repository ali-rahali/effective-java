package item01;

import java.util.Arrays;

/**
 * There're lots of benefits of using simplified static factory methods
 * instead of plain-old constructors
 */
public class StaticFactoryMethod {

    public static void main(String[] args) {
        // Advantages:
        // 1. static factory methods have names:
        Box.ofNumber(1);
        Box.ofLetter('A');

        // 2. it's possible to create methods with the same param list
        Box.ofMinimalNumber(1, 2, 3);
        Box.ofMaximumNumber(1, 2, 3);

        // 3. it's possible to control whether create new instances or not
        Box.ofMinimumNumber();
        Box.ofMaximumNumber();

        // 4. it's possible to return any sub-type of declared return type
        Container<Boolean> container = Container.construct(true);

        // 5. returned class need not exist when the factory method is written:
        // as an example: JDBC is based on that principle

        // Disadvantages:
        // 1. Classes without plain public or protected constructors cannot be subclassed
        /*
        class LittleBox<T> extends Box<T> {
            // no default constructor available
        }
        */

        // 2. Static factories do not stand out in API documentation
        // Therefore some conventional prefixes are used: 'of', 'from', 'newType', 'getType', etc...
    }

    private static class Box<T> implements Container<T> {

        private static final Box<Integer> withMinimumNumber = new Box<>(Integer.MIN_VALUE);
        private static final Box<Integer> withMaximumNumber = new Box<>(Integer.MAX_VALUE);

        private T item;

        private Box(T item) {
            this.item = item;
        }

        /**
         * The first huge advantage is that static factory methods have names
         *
         * @param number for which new {@link Box} instance is created
         * @return newly created object, which contains provided number
         */
        public static Box<Integer> ofNumber(int number) {
            return new Box<>(number);
        }

        /**
         * One more example with static factory method describing what type
         * of box is gonna to be produced
         *
         * @param letter - character for which new {@link Box} instance is created
         * @return newly created object, wrapped over provided #letter
         * @throws IllegalArgumentException in case provided constructor arg is not a
         *                                  letter
         */
        public static Box<Character> ofLetter(char letter) {
            if (!Character.isLetter(letter)) {
                throw new IllegalArgumentException("Provided character is not a letter");
            }
            return new Box<>(letter);
        }

        /**
         * The second advantage - it's possible to create methods with the same
         * list of method parameters
         *
         * @param numbers from which the most minimal is chosen
         * @return new {@link Box} instance wrapped over the minimal value from provided numbers set
         */
        public static Box<Integer> ofMinimalNumber(int... numbers) {
            if (numbers == null) {
                throw new IllegalArgumentException("Should specify at least one argument");
            }

            int minimalNumber = Arrays.stream(numbers)
                    .min()
                    .orElseThrow(() -> new IllegalStateException("Failed to find minimal number"));

            return new Box<>(minimalNumber);
        }

        /**
         * Compare {@link #ofMinimalNumber(int...)} and #ofMaximumNumber(int...) methods.
         * They have both the same method parameters list, but logic within each
         * factory method is totally different.
         * <p>
         * With static factory method it's really easy to spot the difference and logic
         * behind those methods
         *
         * @param numbers from which the maximum is chosen
         * @return new {@link Box} instance wrapped over the maximum value
         */
        public static Box<Integer> ofMaximumNumber(int... numbers) {
            if (numbers == null) {
                throw new IllegalArgumentException("Should specify at least one argument");
            }

            int maximumNumber = Arrays.stream(numbers)
                    .max()
                    .orElseThrow(() -> new IllegalStateException("Failed to find maximum number"));

            return new Box<>(maximumNumber);
        }

        /**
         * It's also possible to control creation of new instances.
         * Method actually never created new instance of a class.
         */
        public static Box<Integer> ofMinimumNumber() {
            return withMinimumNumber;
        }

        /**
         * The same as {@link #ofMinimumNumber()} - new instance is never created
         */
        public static Box<Integer> ofMaximumNumber() {
            return withMaximumNumber;
        }

        @Override
        public T getItem() {
            return item;
        }
    }

    public interface Container<T> {
        T getItem();

        static <U> Container<U> construct(U item) {
            return new Box<>(item);
        }
    }
}
