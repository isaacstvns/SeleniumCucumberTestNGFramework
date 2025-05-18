@tag
Feature: Purchase order from Ecommerce website

	Background:
	Given I landed on Ecommerce Page
	
  @E2E
  Scenario Outline: Submit Order Successfully
    Given Logged in with <username> and <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page

    Examples: 
      | username  					 | password 						| productName			|
      | hello_world@abc.com  | 123456789Hello_World | ZARA COAT 3     |
      | hello_world!@abc.com | 1234Hello 						| ADIDAS ORIGINAL |
