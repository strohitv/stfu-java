package tv.strohi.stfu.gui.version;

public final class Version {

    private static final String VERSION = "1.5.0-SNAPSHOT";
    private static final String GROUPID = "tv.strohi.stfu";
    private static final String ARTIFACTID = "stfu-gui";
    private static final String REVISION = "${buildNumber}";

    public static String getVersion() {
        return VERSION;
    }

    public static String getGroupId() {
        return GROUPID;
    }

    public static String getArtifactId() {
        return ARTIFACTID;
    }

    public static String getRevision() {
        return REVISION;
    }
}
