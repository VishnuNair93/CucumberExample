Feature: Verify User is able to perform Amazon Search

  Scenario Outline: User navigates to Amazon and performs search
    Given User navigates to "<URL>"
    When User searches for the Amazon - "<Product>"
    Then Verify the Amazon Results and click on the Product - "<Product>"
    When User clicks Add to Cart for the Product - "<Product>"
    Then Verify the Product - "<Product>" added to the Cart


    Examples:
      | URL    | Product                         |
      | Amazon | Apple iPhone 15 (256 GB) - Blue |

