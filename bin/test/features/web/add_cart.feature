Feature: Add to Cart Demoblaze
  As a user
  I want to add products to my cart
  So that I can purchase them

  @web
  Scenario: Add product to cart (Positive)
    Given I am on the Demoblaze homepage
    When I select product "Samsung galaxy s6"
    And I add the product to the cart
    Then the product should appear in the cart

  @web
  Scenario: Add same product twice (Negative)
    Given I am on the Demoblaze homepage
    When I select product "Samsung galaxy s6"
    And I add the product to the cart
    And I add the product to the cart again
    Then I should see a duplicate product warning or only one product in the cart

  @web
  Scenario: Remove product from cart (Negative)
    Given I have product "Samsung galaxy s6" in the cart
    When I remove the product from the cart
    Then the cart should be empty
