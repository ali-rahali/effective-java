package effective.item05;

/**
 * An object which is dependent on some external resources doesn't have to
 * control instantiation of these resources by itself. This leads to <b>inflexible</b>
 * and <b>untestable</b> code. Preferred approach: usage of dependency injection design
 * pattern.
 */
public class DependencyInjection {

    /**
     * A road vehicle with (usually) four wheels.
     */
    public static class Car {

        private final WheelFactory wheelFactory;

        /**
         * Dependency injection approach has the following advantages:<br>
         * 1. Implementation of {@link WheelFactory} may even not exist<br>
         * 2. Loose coupling<br>
         * 3. Easy to test<br>
         */
        public Car(WheelFactory wheelFactory) {
            this.wheelFactory = wheelFactory;
        }
    }

    /**
     * A generic factory interface.
     *
     * @param <T> - type of an object to produce.
     */
    public interface AbstractFactory<T> {

        /**
         * @return a (potentially) new instance of specific {@link T} type.
         */
        T create();
    }

    /**
     * A factory for producing {@link Wheel wheels}.
     */
    public interface WheelFactory extends AbstractFactory<Wheel> {
    }

    /**
     * A circular object that turns when a vehicle is moving.
     */
    public interface Wheel {
    }
}
