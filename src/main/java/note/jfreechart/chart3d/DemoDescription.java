package note.jfreechart.chart3d;

/**
 * A description of a demo application.
 */
public class DemoDescription {

    private final String className;

    private final String fileName;

    private final String descriptionFileName;

    /**
     * Creates a new description.
     *
     * @param demoClassName the class name.
     * @param fileName      the file name.
     */
    public DemoDescription(String demoClassName, String fileName,
            String descriptionFileName) {
        this.className = demoClassName;
        this.fileName = fileName;
        this.descriptionFileName = descriptionFileName;
    }

    /**
     * Returns the class name.
     *
     * @return The class name.
     */
    public String getClassName() {
        return this.className;
    }

    /**
     * Returns the file name.
     *
     * @return The file name.
     */
    public String getFileName() {
        return this.fileName;
    }

    public String getDescriptionFileName() {
        return this.descriptionFileName;
    }

    /**
     * Returns the class description.
     *
     * @return The class description.
     */
    @Override
    public String toString() {
        return this.fileName;
    }

}