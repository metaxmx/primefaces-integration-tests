#!/usr/bin/env bash

set -euf

LATEST_CHROME_VERSION="`google-chrome-stable --version | sed -E 's/(^Google Chrome |\.[0-9]+ )//g'`"
LATEST_CHROMEDRIVER_VERSION="`curl -s https://chromedriver.storage.googleapis.com/LATEST_RELEASE`"
CHROMEDRIVER_FILENAME="chromedriver_linux64.zip"

printf "LATEST_CHROME_VERSION=%s\nLATEST_CHROMEDRIVER_VERSION=%s\n" "${LATEST_CHROME_VERSION}" "${LATEST_CHROMEDRIVER_VERSION}"

curl -sOL "https://chromedriver.storage.googleapis.com/${LATEST_CHROMEDRIVER_VERSION}/${CHROMEDRIVER_FILENAME}"
unzip -o "${CHROMEDRIVER_FILENAME}"

set +euf
