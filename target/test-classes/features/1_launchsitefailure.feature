
Feature:  Launch Website Negative Scenario
 # Negative Scenario
  Scenario: TS_02_TC_Negative - Website launch failure in different browsers
    
    Given the user enters an invalid website URL in the browser
    When the system should not load the Ixigo website