[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Build Status](https://travis-ci.org/primefaces-extensions/primefaces-integration-tests.svg?branch=master)](https://travis-ci.org/primefaces-extensions/primefaces-integration-tests)
[![Stackoverflow](https://img.shields.io/badge/StackOverflow-primefaces-chocolate.svg)](https://stackoverflow.com/questions/tagged/primefaces-extensions)

PrimeFaces Integration Tests
==========================

To provide an integration and regression test suite for PrimeFaces.

### Prerequisites

This project uses [selenium webdriver](https://www.selenium.dev/) for web browser automation.  Currently, a native
installation of [Firefox](https://firefox.com/) is required, along with the
[selenium gecko webdriver](https://github.com/mozilla/geckodriver) binary.

### Build & Run
- Build by source `mvn clean package`
- Run "integration tests" `mvn verify`
  - By default, the webdriver binary is loaded from the base directory of the project with an assumed file name of
      "geckodriver" for Linux/MacOS and geckdriver.exe for Windows.  A non-default path can be specified via a system
      property:

      ```mvn verify -Dwebdriver.firefoxDriverBinary=$HOME/.selenium/drivers/firefox/geckdriver```
  - By default, the tests will be run in a visible browser, but can be run in headless mode by activating the
  headless-mode profile:

      ```mvn verify -Pheadless-mode```  
