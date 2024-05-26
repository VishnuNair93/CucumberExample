Feature: Verify User is able to perform Google Search

  Scenario Outline: User navigates to Google and performs search
    Given User navigates to "<URL>"
    When User searches for the Product - "<Product>"
    Then Verify the result shows for the Product - "<Product>"


    Examples:
      | URL    | Product      |
      | Google | Apple iPhone |

