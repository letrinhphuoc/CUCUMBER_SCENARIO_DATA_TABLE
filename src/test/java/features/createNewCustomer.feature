@test
Feature: Title of your feature
  I want to use this template for my feature file

  Scenario: Login to live guru site
    Given I open browser
    And I get login url
    When I click to here link
    And I input to email textbox
    And I click to submit button at register page
    And I get to username information
    And I get to password infomation
    And I open to login page
    And I input to username textbox
    And I input to password textbox
    And I click to login button
    Then Verify home page welcom message is displayed
    When I click to New Customer page
    And I input all information to this page
      | CustomerName    | Gender | DateOfBirth | Address     | City | State   | Pin    | Phone     | Email | Password |
      | Automation Test | m      | 24/09/1995  | 123 Address | HCM  | VietNam | 123456 | 123456789 | auto  |   123456 |
    And I click to submit button 
    And I close the browser
