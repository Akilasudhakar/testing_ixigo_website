Feature: Apply filters and click 1st flight

Scenario:Booking flights successfully
Given the user is on login page
And the user click on the round trip
When the user enter boarding place as "<BoardingPlace>" and landing place as "<LandingPlace>"
And the user selects the departure date as "<DepartureDate>" and return date as "<ReturnDate>"
And the user clicks the travellers & class options increase the value for adults as "<Adult>" and child as "<Child>"
And the user select the business class as "<Class>" and clicks done button
And the user clicks on the search flight button

And the user goes to the filter, scroll down till you see stops enter as "<stops>"  and click on the airlines enter as "<Airlines>" and click on the departure time as "<DepartureTime>"
And the user clicks on the first displayed flight
And the user clicks on the book button
Then the user validates the search result page has the offers for you and capture screen shot 