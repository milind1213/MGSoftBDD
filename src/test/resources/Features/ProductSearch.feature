Feature: Filtering Products on an E-commerce Website

  Scenario: Applying Filters to Search for Phones
    Given the user is on the E-commerce website
    When the user clicks on Shop By Category
    And the user selects Cell Phones and Accessories option
    And the user applies the "Condition" filter and selects "New"
    And the user applies the "Price" filter with a range from "230" to "500"
    And the user applies the "Item Location" filter and selects "US Only"
    Then the applied "Condition" filter tag should be displayed and match "New"
    And the applied "Price" filter tag should be displayed
    And the applied "Item Location" filter tag should be displayed and match
