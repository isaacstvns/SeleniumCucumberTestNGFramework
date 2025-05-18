@tag
Feature: Login functionality

  @Smoke
  Scenario Outline: Invalid Login 
    Given I landed on Ecommerce Page
    Given Logged in with <username> and <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username  					| password 		 	 |
      | hello_world@abcde.com | 123456789Hello | 
			| hello_world@abcde.com | 123456789Hella |
