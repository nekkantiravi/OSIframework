package test.automation.framework;

import com.rajatthareja.reportbuilder.Color;
import com.rajatthareja.reportbuilder.ReportBuilder;
import cucumber.api.cli.Main;

import cucumber.runtime.ClassFinder;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import cucumber.runtime.model.CucumberFeature;
import gherkin.events.PickleEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.*;

import static test.automation.framework.Config.*;
import static test.automation.framework.Config.getReportsDir;

public final class Runner {

    private static int exitStatus = 0;
    private static int buildCount = 0;
    private final static Logger logger = Logger.getLogger("Runner");

    public static void main(String... args) {
        prepareReportDir();
        runCucumber(getCucumberArgs(args));
        buildReport();
        Browser.quit();
        System.exit(exitStatus);
    }

    public static Logger log() {
        return logger;
    }

    private static String[] getCucumberArgs(String... args) {
        List<String> cucumberArgs = new LinkedList<>();
        cucumberArgs.add("--glue");
        cucumberArgs.add(getGlue());
        cucumberArgs.add("--plugin");
        cucumberArgs.add("json:" + getReportsDir() + "report.json");
        cucumberArgs.add("--plugin");
        cucumberArgs.add("rerun:" + getReportsDir() + "rerun.txt");
        cucumberArgs.add("--plugin");
        cucumberArgs.add("html:" + getReportsDir() + "cucumber");
        cucumberArgs.add("--plugin");
        cucumberArgs.add("pretty");
        if (getTags() != null) {
            cucumberArgs.add("--tags");
            cucumberArgs.add(getTags());
        }
        if (getScenarios() != null) {
            cucumberArgs.add("--name");
            cucumberArgs.add(getScenarios());
        }
        if (isDryRun()) {
            cucumberArgs.add("--dry-run");
        }
        cucumberArgs.add("--strict");
        cucumberArgs.addAll(Arrays.asList(args));
        if (getFeatures() != null) {
            if (getFeatures().startsWith("@")) {
                cucumberArgs.add(getFeatures());
            } else {
                cucumberArgs.add("classpath:" + Config.getClassPath("features") + getFeatures());
            }
        }
        log().info("Cucumber Options: " + cucumberArgs);
        return cucumberArgs.toArray(new String[cucumberArgs.size()]);
    }

    private static String[] getCucumberRerunArgs() {
        List<String> cucumberArgs = new LinkedList<>();
        cucumberArgs.add("--glue");
        cucumberArgs.add(getGlue());
        cucumberArgs.add("--plugin");
        cucumberArgs.add("json:" + getReportsDir() + "rerun.json");
        cucumberArgs.add("--plugin");
        cucumberArgs.add("html:" + getReportsDir() + "cucumber/rerun");
        cucumberArgs.add("--plugin");
        cucumberArgs.add("pretty");
        cucumberArgs.add("--strict");
        cucumberArgs.add("@" + getReportsDir() + "rerun.txt");
        log().info("Cucumber Options: " + cucumberArgs);
        return cucumberArgs.toArray(new String[cucumberArgs.size()]);
    }

    private static void runCucumber(String... cucumberArgs) {
        try {
            exitStatus = Main.run(cucumberArgs, Thread.currentThread().getContextClassLoader());
        } catch (Throwable e) {
            e.printStackTrace();
            exitStatus = 1;
        }
    }

    private static void buildReport() {
        ReportBuilder reportBuilder = new ReportBuilder();
        reportBuilder.setReportDirectory(getReportsDir());
        reportBuilder.setReportColor(Color.CYAN);
        reportBuilder.setAdditionalInfo("Date", LocalDateTime.now().toString());
        reportBuilder.setAdditionalInfo("URL", getUrl());
        reportBuilder.setAdditionalInfo("Browser", getBrowser());
        if (getBrowserVersion() != null) {
            reportBuilder.setAdditionalInfo("Browser Version", getBrowserVersion());
        }
        reportBuilder.setAdditionalInfo("Platform", getPlatform() != null ? getPlatform() : System.getProperty("os.name"));
        if (getPlatformVersion() != null) {
            reportBuilder.setAdditionalInfo("Platform Version", getPlatformVersion());
        }
        if (getDevice() != null) {
            reportBuilder.setAdditionalInfo("Device", getDevice());
        }

        if (buildCount > 0) {
            List<Object> jsonReports = new LinkedList<>();
            for (int i = 0; i < buildCount; i++) {
                jsonReports.add(new File(getReportsDir() + "build" + i + "/report.json"));
            }
            reportBuilder.build(jsonReports);

            if (isRerun()) {
                jsonReports.clear();
                reportBuilder.setReportFileName("rerun");
                for (int i = 0; i < buildCount; i++) {
                    if (Files.exists(Paths.get(getReportsDir() + "build" + i + "rerun.json"))) {
                        jsonReports.add(new File(getReportsDir() + "build" + i + "/rerun.json"));
                    }
                }
                reportBuilder.build(jsonReports);

                jsonReports.clear();
                reportBuilder.setReportFileName("final_report");
                for (int i = 0; i < buildCount; i++) {
                    jsonReports.add(new File(getReportsDir() + "build" + i + "/report.json"));
                    if (Files.exists(Paths.get(getReportsDir() + "build" + i + "rerun.json"))) {
                        jsonReports.add(new File(getReportsDir() + "build" + i + "/rerun.json"));
                    }
                }
                reportBuilder.build(jsonReports);
            }
        } else {
            reportBuilder.build(new File(getReportsDir() + "report.json"));

            if (isRerun() && Files.exists(Paths.get(getReportsDir() + "rerun.json"))) {
                reportBuilder.setReportFileName("rerun");
                reportBuilder.build(new File(getReportsDir() + "rerun.json"));

                reportBuilder.setReportFileName("final_report");
                reportBuilder.build(new File(getReportsDir() + "report.json"), new File(getReportsDir() + "rerun.json"));
            }
        }
    }

    private static void prepareReportDir() {
        new File(getReportsDir()).mkdirs();
        Arrays.asList("report.json", "report.html", "cucumber/report.js",
                "rerun.txt", "rerun.json", "rerun.html", "cucumber/rerun/report.js",
                "final_report.html").forEach(file -> new File(getReportsDir() + file).delete());
    }

    private static String getGlue() {
        return Runner.class.getName().replace(".framework.Runner", "");
    }
}
