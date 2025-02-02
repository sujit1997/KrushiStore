#Author : Sujit Manmode
#
#@SmokeScenario
Feature: feature to test login funcionality

#@SmokeTest
Scenario: check login is successful with valid credentials

Given user is on login page
When user enters username and password
And clicks on login button
Then user is navigated to the homepage

