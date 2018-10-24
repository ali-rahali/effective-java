package item03;

/**
 * Singleton - a class which is instantiated exactly once
 */
public class Singleton {

    /**
     * Singleton example which uses public static final field
     */
    public static class EagerFieldSingleton {

        public static final EagerFieldSingleton INSTANCE = new EagerFieldSingleton();

        /**
         * Restrict instance creation
         */
        private EagerFieldSingleton() {
            // protection against reflection
            if (INSTANCE != null) {
                throw new IllegalStateException("New instance creation is forbidden");
            }
        }
    }

    /**
     * Singleton example which uses static factory instead of public final field.
     * The main advantage of this approach is that, one can easily refuse of using singleton
     * without changing its API.
     */
    public static class EagerFactoryMethodSingleton {

        private static final EagerFactoryMethodSingleton INSTANCE = new EagerFactoryMethodSingleton();

        /**
         * Static factory method which returns the same instance each time.
         * It's also possible to switch to lazy field initialization.
         */
        public EagerFactoryMethodSingleton getInstance() {
            return INSTANCE;
        }
    }

    /**
     * Enum singleton is the most preferable approach as its intrinsically defends
     * against reflection and deserialization attacks.
     * <p>
     * Disadvantage of this approach is that it implicitly extends from {@link Enum} class,
     * therefore it cannot extend any other class.
     */
    public enum EnumSingleton {
        INSTANCE
    }
}
