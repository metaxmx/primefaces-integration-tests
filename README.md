[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Actions Status](https://github.com/primefaces-extensions/primefaces-integration-tests/workflows/Java+CI/badge.svg)](https://github.com/{owner}/primefaces-integration-tests/actions)
[![Stackoverflow](https://img.shields.io/badge/StackOverflow-primefaces-chocolate.svg)](https://stackoverflow.com/questions/tagged/primefaces-extensions)

PrimeFaces Integration Tests
----------------------------

To provide an integration and regression test suite for PrimeFaces.

## Prerequisites

This project uses [selenium webdriver](https://www.selenium.dev/) for web browser automation.  Currently, a native
installation of [Firefox](https://firefox.com/) and/or [Chrome](https://www.google.com/chrome/) is required.
Additionally, the selenium driver corresponding to the browser(s) must also be installed.  These can be downloaded
from the following locations:

- [selenium gecko webdriver](https://github.com/mozilla/geckodriver)
- [select chrome webdriver](https://chromedriver.chromium.org/)

## Build & Run
- Build by source: `mvn clean package`
- Run "integration tests" with the _verify_ phase: `mvn verify`

### Firefox (default)

The default configuration of the project runs tests with a visible Firefox browser.  This requires geckodriver in order
to allow selenium to control the browser.  This is a binary file that can be downloaded via the link above, and can be
placed in the base directory of the project where it will be accessed by selenium for running the tests.  The default
location for the binary is the base directory of the project with an assumed file name of `geckodriver`
for Linux/MacOS and `geckodriver.exe` for Windows, but a non-default path can be specified via a system property:

    mvn verify -Dwebdriver.firefoxDriverBinary=$HOME/.selenium/drivers/firefox/geckodriver
  
The tests will be run by default in a visible browser UI, but can be run in headless mode by activating the
headless-mode maven profile:

    mvn verify -Pheadless-mode
      
### Chrome

Google Chrome may also be used to run the tests by activating the "chrome" maven profile.  This can be done on the
commandline with the -P maven parameter:
 
    mvn verify -Pchrome
  
Using chrome to run the tests requires the "chromedriver" binary which can be downloaded via the link above, and can be
placed in the base directory of the project.  Selenium will access the binary from the base directory with an assumed
file name of `chromedriver` for Linux/MacOS and `geckodriver.exe` for Windows, but a non-default path can be specified
via a system property:
 
      mvn verify -Dwebdriver.chromeDriverBinary=$HOME/.selenium/drivers/chrome/chromedriver -Pchrome

When using chrome, the headless-mode maven profile may be activated to run chrome in headless mode:

     mvn verify -Pchrome,headless-mode
