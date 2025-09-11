Feature: Story 2 - login Website
  

 # Positive Scenario
  Scenario: TS_03_TC_Positive - User login with valid credentials
    Given the user enters the valid Ixigo website URL in the browser and the user is on the Ixigo Login/Signup page
    When the user enters valid mobile number as "<mobile_no>"
    And the user enters a valid email address
    And the user enters the correct OTP
    And the user submits the OTP within the valid time frame
    
    Then the system should authenticate the user successfully
    

  
    
