package effective.item08;

/**
 * There're two legitimate usages of java finalizers and cleaners:
 * 1. safety net
 * 2. termination of noncritical native resources
 */
public class AvoidFinalizers {

    private static class Building implements AutoCloseable {

        private boolean windowsClosed;

        @Override
        public void close() throws Exception {
            windowsClosed = true;
        }

        /**
         * In case client's code doesn't invoke {@link #close()} method -
         * this one might be invoked, thus resulting in all windows being closed.
         */
        @Override
        protected void finalize() throws Throwable {
            close();
        }
    }
}
