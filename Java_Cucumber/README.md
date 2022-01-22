# JavaCucumber

**Java Cucumber Selenium Rest Assured Test Automation Framework**

## Execution

**Main Class:** test.automation.framework.Runner

```
set url=https://www.google.co.in

set features=Google/Search.feature

mvn clean compile exec:java
```

## Environment Variables

**url** Base URL (_http://localhost_)
    
**browser** Browser Name (_chrome_)

**browser_version** Browser Version

**features** Feature File Path

**tags** Cucumber Tags

**scenarios** Scenario Name

**dry_run** Do Dry Run of Feature Files (_false_)

**rerun** Enable Automatic Rerun of Failed Scenarios (_false_)

**platform_name** Operating System

**platform_version** Operating System Version

**device** Device Name

**appium_version** Appium Version

**remote_url** Grid URL or Appium Server URL or Cloud URL

**db_driver** DB Driver Class Name

**db_url** DB URL

**db_username** DB Username

**db_password** DB Password

**reports_dir** Reports Dir Path (_reports/_)

**report_recipients** Email IDs for sending Report

**keep_browser** Use same browser instance for all scenarios

**partition_size** Scenarios per build for parallel execution

## Steps

* I visit "" page
* I should be on "" page
* I should see following elements:
* I should see "" element
* I should see following elements on "" page:
* I should see "" element on "" page
* I click on "" element
* I click on "" element on "" page
* I should see "" panel
* I should see "" panel on "" page
* I should see "" element on "" panel
* I should see "" element on "" panel on "" page
* I should see following elements on "" panel:
* I should see following elements on "" panel on "" page:
* I click on "" element on "" panel
* I click on "" element on "" panel on "" page
* I "" on current page
* I "" on "" page
* I "" on "" panel
* I "" on "" panel on "" page
* I "" with "" on current page
* I "" with "" on "" page
* I "" with "" on "" panel
* I "" with "" on "" panel on "" page
* I select random value in "" dropdown
* I select random value in "" dropdown on "" page
* I select random value in "" dropdown on "" panel
* I select random value in "" dropdown on "" panel on "" page
* I select "" value in "" dropdown
* I select "" value in "" dropdown on "" page
* I select "" value in "" dropdown on "" panel
* I select "" value in "" dropdown on "" panel on "" page
* I select "" text in "" dropdown
* I select "" text in "" dropdown on "" page
* I select "" text in "" dropdown on "" panel
* I select "" text in "" dropdown on "" panel on "" page
* I select random "" radio button
* I select random "" radio button on "" page
* I select random "" radio button on "" panel
* I select random "" radio button on "" panel "" on page
* I select "" in "" radio buttons
* I select "" in "" radio buttons on "" page
* I select "" in "" radio buttons on "" panel
* I select "" in "" radio buttons on "" panel on "" page
* I select "" check box
* I select "" check box on "" page
* I select "" check box on "" panel
* I select "" check box on "" panel on "" Page
* I type "" in "" text box
* I type "" in "" text box on "" page
* I type "" in "" text box on "" panel
* I type "" in "" text box on "" panel on "" page
* I should see "" in "" element
* I should see "" in "" element on "" page
* I should see "" in "" element on "" panel
* I should see "" in "" element on "" panel on "" page
* I should see "" in "" list
* I should see "" in "" list on "" page
* I should see "" in "" list on "" panel
* I should see "" in "" list on "" panel on "" page
* I should see "" is enabled
* I should see "" is enabled on "" page
* I should see "" is enabled on "" panel
* I should see "" is enabled on "" panel on "" page
* I should see following in "" list:
* I should see following in "" list on "" page:
* I should see following in "" list on "" panel:
* I should see following in "" list on "" panel on "" page:
* I fill "" form with following:
* I fill "" form on "" page with following:
* I fill "" form on "" panel with following:
* I fill "" form on "" panel on "" page with following:
* I should see following panels:
* I should see following panels on "" page:

* the status code is (\d+)
* response includes the following:
* response includes the following in any order:

## Tags

**@Screenshot** Takes screenshot of final page

**@KeepBrowser** Keep Browser for next scenario