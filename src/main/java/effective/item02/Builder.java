package effective.item02;

/**
 * Static factory methods as well as plain constructors share the same
 * huge limitation: they're unwieldy (do not scale well) with lots of
 * optional / required parameters.
 */
public class Builder {

    public static void main(String[] args) {
        // using fluent API complex object creation is very readable
        Notebook notebook = new Notebook
                .NotebookBuilder("Intel", "Rosewill", "IntelCore i5")
                .computerCase("Master Box")
                .videoCard("GeForce")
                .inputDevice("Genius")
                .build();
    }

    /**
     * Dummy imitation of a notebook consisting of required and optional parts.
     * Is implemented using telescoping approach.
     */
    public static class TelescopingNotebook {
        // let's assume that these parts are required
        private String motherboard;
        private String powerSupply;
        private String cpu;

        // let's assume that these parts are optional
        private String computerCase;
        private String videoCard;
        private String inputDevice;
        private String outputDevice;

        // lots of telescoping constructors
        public TelescopingNotebook(String motherboard, String powerSupply, String cpu) {
            this(motherboard, powerSupply, cpu, null);
        }

        public TelescopingNotebook(String motherboard, String powerSupply, String cpu, String computerCase) {
            this(motherboard, powerSupply, cpu, computerCase, null);
        }

        public TelescopingNotebook(String motherboard, String powerSupply, String cpu,
                                   String computerCase, String videoCard) {
            this(motherboard, powerSupply, cpu, computerCase, videoCard, null);
        }

        public TelescopingNotebook(String motherboard, String powerSupply, String cpu,
                                   String computerCase, String videoCard, String inputDevice) {
            this(motherboard, powerSupply, cpu, computerCase, videoCard, inputDevice, null);
        }

        public TelescopingNotebook(String motherboard, String powerSupply, String cpu, String computerCase,
                                   String videoCard, String inputDevice, String outputDevice) {
            this.motherboard = motherboard;
            this.powerSupply = powerSupply;
            this.cpu = cpu;
            this.computerCase = computerCase;
            this.videoCard = videoCard;
            this.inputDevice = inputDevice;
            this.outputDevice = outputDevice;
        }
    }

    /**
     * Fields initialization is implemented using JavaBeans pattern.
     * Main limitation: inconsistent state.
     */
    public static class JavaBeansNotebook {
        private String motherboard;
        private String powerSupply;
        private String cpu;
        private String computerCase;
        private String videoCard;
        private String inputDevice;
        private String outputDevice;

        public void setMotherboard(String motherboard) {
            this.motherboard = motherboard;
        }

        public void setPowerSupply(String powerSupply) {
            this.powerSupply = powerSupply;
        }

        public void setCpu(String cpu) {
            this.cpu = cpu;
        }

        public void setComputerCase(String computerCase) {
            this.computerCase = computerCase;
        }

        public void setVideoCard(String videoCard) {
            this.videoCard = videoCard;
        }

        public void setInputDevice(String inputDevice) {
            this.inputDevice = inputDevice;
        }

        public void setOutputDevice(String outputDevice) {
            this.outputDevice = outputDevice;
        }
    }

    public static class Notebook {
        private String motherboard;
        private String powerSupply;
        private String cpu;

        private String computerCase;
        private String videoCard;
        private String inputDevice;
        private String outputDevice;

        // do not allow directly instantiate
        private Notebook(NotebookBuilder notebookBuilder) {
            this.motherboard = notebookBuilder.motherboard;
            this.powerSupply = notebookBuilder.powerSupply;
            this.cpu = notebookBuilder.cpu;
            this.computerCase = notebookBuilder.computerCase;
            this.videoCard = notebookBuilder.videoCard;
            this.inputDevice = notebookBuilder.inputDevice;
            this.outputDevice = notebookBuilder.outputDevice;
        }

        public static class NotebookBuilder {
            // builder has the same fields as related class
            private String motherboard;
            private String powerSupply;
            private String cpu;

            private String computerCase;
            private String videoCard;
            private String inputDevice;
            private String outputDevice;

            /**
             * It's always good idea to apply some constraints using builder's constructor
             */
            public NotebookBuilder(String motherboard, String powerSupply, String cpu) {
                this.motherboard = motherboard;
                this.powerSupply = powerSupply;
                this.cpu = cpu;
            }

            private NotebookBuilder computerCase(String computerCase) {
                this.computerCase = computerCase;
                return this;
            }

            private NotebookBuilder videoCard(String videoCard) {
                this.videoCard = videoCard;
                return this;
            }

            private NotebookBuilder inputDevice(String inputDevice) {
                this.inputDevice = inputDevice;
                return this;
            }

            private NotebookBuilder outputDevice(String outputDevice) {
                this.outputDevice = outputDevice;
                return this;
            }

            /**
             * The main builder method which passes itself to the related
             * constructor of a {@link Notebook} class
             */
            private Notebook build() {
                return new Notebook(this);
            }
        }
    }
}
