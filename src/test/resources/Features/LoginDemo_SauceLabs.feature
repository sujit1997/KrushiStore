Feature: test login functionality
  Scenario Outline: Check login is successful with valid credentials

  Given browser id open
  And user is onlogin page
  When user enters "<username>" and "<password>"
  Then user is navigated to the homepage

  Examples:
  |username|password|
  |standard_user|secret_sauce|