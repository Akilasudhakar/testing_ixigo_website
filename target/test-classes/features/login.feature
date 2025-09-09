Feature: Story 2 - login Website
  

 # Positive Scenario
  Scenario: TS_03_TC_Positive - User login with valid credentials
    Given the user enters the valid Ixigo website URL in the browser and the user is on the Ixigo Login/Signup page
    When the user enters valid mobile number as "<mobile_no>"
    And the user enters a valid email address
    And the user enters the correct OTP
    And the user submits the OTP within the valid time frame
    
    Then the system should authenticate the user successfully
    

  # Negative Scenario
  Scenario: TS_04_TC_Negative - User login with invalid credentials
   
    When the user enters invalid mobile number as "<mobile_no>"
    
    When the user enters an incorrect OTP
    When the user fails to enter the OTP within the time frame
    When the user tries to submit without entering an OTP
    
