Feature: Place Order Demoblaze
  As a user
  I want to place an order for products in my cart
  So that I can complete my purchase

  @web
  Scenario: Place order after adding product to cart
    Given I am on the Demoblaze homepage
    When I select product "Samsung galaxy s6"
    And I add the product to the cart
    And I go to the cart
    And I click Place Order
    And I enter order details "Test User" "test@demo.com" "1234567890" "Test Address" "Test City" "Test Country"
    And I confirm the purchase
    Then I should see the Thank you! message
