Feature: Story 1 - Launch Website
  

  # Positive Scenario
  Scenario: TS_01_TC_Positive - Launch website successfully in different browsers
  
    Given the user enters the valid Ixigo website URL in the browser
    When the website should open successfully
    Then the website should display correctly in Chrome, Firefox, and Edge

  # Negative Scenario
  Scenario: TS_02_TC_Negative - Website launch failure in different browsers
    
    Given the user enters an invalid website URL in the browser
    When the system should not load the Ixigo website
    
