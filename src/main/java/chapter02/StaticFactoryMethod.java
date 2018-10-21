package chapter02;

import java.util.Arrays;

/**
 * There're lots of benefits of using simplified static factory methods
 * instead of plain-old constructors
 */
public class StaticFactoryMethod {

    public static void main(String[] args) {
        // 1. static factory methods have names:
        Box.ofNumber(1);
        Box.ofLetter('A');

        // 2. it's possible to create methods with the same param list
        Box.ofMinimalNumber(1, 2, 3);
        Box.ofMaximumNumber(1, 2, 3);
    }

    private static class Box<T> {
        private T item;

        public Box(T item) {
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

        public T getItem() {
            return item;
        }
    }
}
