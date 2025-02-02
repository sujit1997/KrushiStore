#Author
#
#@SmokeScenario
Feature: feature to test login funcionality

#@SmokeTest
Scenario: check login is successful with valid credential

Given user is on login page
When user enters username and password
And clicks on login button
Then user is navigated to the homepage

