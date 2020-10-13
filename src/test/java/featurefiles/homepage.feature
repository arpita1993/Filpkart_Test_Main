#Feature file for flipkart homepage. opens the page and searches or an item

  Feature: Homepage search functionality
    Background:
      Given User has opened chrome browser and  navigated to flipkart homepage
      And User has closed the login popup


    Scenario: Searching for iphone on home page navigates to results for the same
      When On entering "iphone" in search field
      And clicking on search icon
      And User filters for phones greater than 30000
      And Sorts the results by price in ascending order
      Then It collects the list of phones whose price is less than 40000
      And Generate the data into csv

      #Then Another page should load with results for iphone

