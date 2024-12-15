@login
Feature: User Login

  @core @positive @aut_tester1
  Scenario Outline: <id> Successful Login_Normal User
    Given user launch url "<url>" on browser "<browser>"
    When user login using username "<username>" and password "<password>"
    Then user should land on Home page

    Examples: 
      | id | url                           | browser | username      | password     |
      |  1 | https://www.saucedemo.com/v1/ | chrome    | standard_user | secret_sauce |

  @core @negative @aut_tester2
  Scenario Outline: <id> Unsuccessful Login_Locked Out User
    Given user launch url "<url>" on browser "<browser>"
    When user login using username "<username>" and password "<password>"
    Then user should see an error message "<error_message>"

    Examples: 
      | id | url                           | browser | username        | password     | error_message                         |
      |  1 | https://www.saucedemo.com/v1/ | edge  | locked_out_user | secret_sauce | Sorry, this user has been locked out. |

  @core @negative @aut_tester3
  Scenario Outline: <id> Unsuccessful Login_Invalid Credentials
    Given user launch url "<url>" on browser "<browser>"
    When user login using username "<username>" and password "<password>"
    Then user should see an error message "<error_message>"

    Examples: 
      | id | url                           | browser | username      | password     | error_message                                               |
      |  1 | https://www.saucedemo.com/v1/ | firefox    | bcn34na       | secret_sauce | Username and password do not match any user in this service |
      |  2 | https://www.saucedemo.com/v1/ | chrome  | standard_user | gfhd34535    | Username and password do not match any user in this service |
      |  3 | https://www.saucedemo.com/v1/ | edge    | jkol463       | ewr3ddf2     | Username and password do not match any user in this service |

  @extended @negative @aut_tester4
  Scenario Outline: <id> Unsuccessful Login_Empty Credentials
    Given user launch url "<url>" on browser "<browser>"
    When user login using username "<username>" and password "<password>"
    Then user should see an error message "<error_message>"

    Examples: 
      | id | url                           | browser | username      | password     | error_message        |
      |  1 | https://www.saucedemo.com/v1/ | firefox  | abcde3        |              | Username is required |
      |  2 | https://www.saucedemo.com/v1/ | chrome    |               | secret_sauce | Username is required |
      |  3 | https://www.saucedemo.com/v1/ | edge  | standard_user |              | Password is required |
