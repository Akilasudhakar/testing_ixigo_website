Feature: Story 1 - Launch Website
  As a user
  I want to launch the Ixigo website
  So that I can access the application in different browsers

  # Positive Scenario
  Scenario: TS_01_TC_Positive - Launch website successfully in different browsers
    Given the user has a valid browser installed
    When the user enters the valid Ixigo website URL in the browser
    Then the website should open successfully
    And the website should display correctly in Chrome, Firefox, and Edge

  # Negative Scenario
  Scenario: TS_02_TC_Negative - Website launch failure in different browsers
    Given the user has a browser open
    When the user enters an invalid website URL in the browser
    Then the system should not load the Ixigo website
    And an error page like "Page not found" or "Invalid URL" should be displayed
