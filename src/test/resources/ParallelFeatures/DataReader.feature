
Feature: Registration

  Background: 
    Given user on the homepage
    And user follows "Sign in"

  Scenario: Create a New User
    When user fills "registration email textbox" with "milind@test.com"
    And user clicks "create an account button"
    And user enters the following details
      | FirstName  |  LastName |
      | MIlind     |  Ghongade |
    
    And user clicks "register button"
