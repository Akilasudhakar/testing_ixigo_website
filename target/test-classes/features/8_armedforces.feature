Feature: Search flight as a normal person

Scenario: Enter the basic details with armedforces offers 


Given the user click on the round trip
When the user enter boarding place as "<BoardingPlace>" and landing place as "<LandingPlace>"
And the user selects the departure date as "<DepartureDate>" and return date as "<ReturnDate>"
And the user clicks the travellers & class options increase the value for adults as "<Adult>" and child as "<Child>"
And the user select the business class as "<Class>" and clicks done button
And the user select the special offers as "<Armedofficeofferoffer>" and clicks
And the user clicks on the search flight button
Then the user validates the search result page has the text filter and capture screen shot 
