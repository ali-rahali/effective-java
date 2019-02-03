package effective.item06;

/**
 * One should always consider reusing existing object, especially when this
 * object is immutable.
 */
public class UnnecessaryObjectCreation {

    public static void main(String[] args) {

        // Extreme example: do not create String objects from string literals.
        String effective = new String("effective");

        // Be careful with implicit autoboxing
        Long sum = 0L;
        for (long index = 0; index <= 10; index++) {
            sum += index; // Autoboxing functionality creates object per each increment
        }

        // As a general rule: immutable objects can always be reused
    }
}
