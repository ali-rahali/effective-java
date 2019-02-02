package effective.item04;

/**
 * There're type of classes which contain only static fields and methods and therefore
 * they are not intended to be instantiated.
 * <p>
 * To forbid instance creation of such classes private constructor must be provided.
 * Also it's possible to create a class final (not actually necessary, but directly denotes
 * that class is not intended to be subclassed)
 */
public final class UtilityClass {

    // suppress creation of class instance
    private UtilityClass() {
        // defend against reflection
        throw new IllegalStateException();
    }

    // some utility methods below
}
