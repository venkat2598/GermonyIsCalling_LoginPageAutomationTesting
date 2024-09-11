# GermonyIsCalling_LoginPageAutomationTesting

# Test LoginPage

I have automated login page covered both positive (Successfull) and negative (UnSuccessfull) scenarios to test the login functionality.I used Data Driven Approach which is used to automate same test script with using different data set configure with TestNG Dataprovider. Managed data through Excel sheet for  easy to integrate with using Apache POI.
I have written a code for run our test script in Multiple Browser Support.

**Website URL** : https://app.germanyiscalling.com/common/login/

# Project Structure:

**Maven Project** - Maintain the dependency and structure of the project.

**Selenium With Java** - which is used for Write an web Application  Automation script using this framework.

**TestNG** - Maintain our testcases,Execution order and run our testcases , Test suite.

**Listeners Package** - Listen our test script start to end if any failure in test then  it will automatically take screenshot and also multiple feature is there inside the listeners.

**Screenshots Folder** - store the failed testcase Screenshots.

**Utils Package** - write a script to read an excel data.

**Base Package** - write a common script for browser setup and teardown,screenshots  script.

**Testcases package** - Write a Testcases script to test a page.

**Drivers Folder** - Used for stored all related browser exe file in the project.

**ExcelLoginData** - used for stored the Excel data sheet File.

**Config - Properties File** - which is used for write a common things like browser name, Website Url,Driver location,Username or password. if we need any data to read using Propertiesclass using load method.

**pom.xml** - maintain our dependencies, project configurations, plugins

# Project Screenshot:
![image](https://github.com/user-attachments/assets/49e28325-4284-43b8-aab3-f5997f32a3bc)











