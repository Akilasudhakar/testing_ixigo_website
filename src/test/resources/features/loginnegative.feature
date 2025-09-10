Feature: Story 2 - loginnegative scenarios
  


  # Negative Scenario
  Scenario: TS_04_TC_Negative - User login with invalid credentials
   
    When the user enters invalid mobile number as "<mobile_no>"
    
    When the user enters an incorrect OTP
    When the user fails to enter the OTP within the time frame
    When the user tries to submit without entering an OTP
    
