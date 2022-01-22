package test.automation.framework;

public class Config {

    private static String url = getEnvVar("url");
    private static String sauceUser = getEnvVar("sauce_user");

    private static String browser = getEnvVar("chrome");
    private static String browserVersion = getEnvVar("browser_version");

    private static String features = getEnvVar("features");
    private static String tags = getEnvVar("tags");
    private static String scenarios = getEnvVar("scenarios");
    private static boolean dryRun = getEnvBoolean("dry_run");
    private static boolean rerun = getEnvBoolean("rerun");

    private static String platform = getEnvVar("platform");
    private static String platformVersion = getEnvVar("platform_version");

    private static String device = getEnvVar("device");
    private static String appiumVersion = getEnvVar("appium_version");

    private static String remoteUrl = getEnvVar("remote_url");

    private static String dbDriver = getEnvVar("db_driver");
    private static String dbUrl = getEnvVar("db_url");
    private static String dbUsername = getEnvVar("db_username");
    private static String dbPassword = getEnvVar("db_password");

    private static String reportsDir = getEnvVar("reports_dir");
    private static String reportRecipients = getEnvVar("report_recipients");

    private static boolean keepBrowser = getEnvBoolean("keep_browser");

    private static int partitionSize = getEnvInt("partition_size");

    private static String getEnvVar(String name) {
        String value = System.getenv(name);
        value = value == null ? null : value.trim();
        if (value != null && !value.isEmpty()) {
            return value;
        }
        return null;
    }

    private static boolean getEnvBoolean(String name) {
        String param = getEnvVar(name);
        return param != null && param.matches("t|true");
    }

    private static int getEnvInt(String name) {
        String param = getEnvVar(name);
        try {
            return param == null ? 0 : Integer.parseInt(param);
        } catch (NumberFormatException e) {
            throw new RuntimeException(name + " should be Integer.");
        }
    }

    public static String getBrowser() {
        if (browser == null) {
            browser = "chrome";
        }
        return browser;
    }

    public static String getUrl() {
        if (url == null) {
            url = "http://localhost";
        }
        return url;
    }

    public static boolean isOSX() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static boolean isLinux() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("nix") || os.contains("nux") || os.contains("aix"));
    }

    public static String getReportsDir() {
        if (reportsDir == null) {
            reportsDir = "reports/";
        }
        return reportsDir;
    }

    public static String getReportRecipients() {
        return reportRecipients;
    }

    public static String getClassPath(String packageName) {
        return Config.class.getName().replace("framework.Config", packageName).replace(".", "/") + "/";
    }

    public static String getRemoteUrl() {
        return remoteUrl;
    }

    public static String getBrowserVersion() {
        return browserVersion;
    }

    public static String getPlatform() {
        return platform;
    }

    public static String getPlatformVersion() {
        return platformVersion;
    }

    public static String getFeatures() {
        return features;
    }

    public static String getTags() {
        return tags;
    }

    public static String getScenarios() {
        return scenarios;
    }

    public static boolean isDryRun() {
        return dryRun;
    }

    public static boolean isRerun() {
        return rerun;
    }

    public static int getPartitionSize() {
        return partitionSize;
    }

    public static String getDevice() {
        return device;
    }

    public static String getSauceUser() {
        return sauceUser;
    }

    public static String getAppiumVersion() {
        return appiumVersion;
    }

    public static String getDbUrl() {
        return dbUrl;
    }

    public static String getDbUsername() {
        return dbUsername;
    }

    public static String getDbPassword() {
        return dbPassword;
    }

    public static String getDbDriver() {
        return dbDriver;
    }

    public static void setDbConfig(String dbDriver, String dbUrl, String dbUsername, String dbPassword) {
        DB.closeConnection();
        Config.dbDriver = dbDriver;
        Config.dbUrl = dbUrl;
        Config.dbUsername = dbUsername;
        Config.dbPassword = dbPassword;
    }

    public static boolean isKeepBrowser() {
        return keepBrowser;
    }
}
