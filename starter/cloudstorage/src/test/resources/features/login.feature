Feature: Users can register and login

  Scenario:    Write a Selenium test that signs up a new user, logs that user in,
  verifies that they can access the home page,
  then logs out and verifies that the home page is no longer accessible.

    Given new user signs up with login "firstUser" and password "sillyPassword"
    When user logs in with login "firstUser" and password "sillyPassword"
    Then home page is accessible
    When  user logs out
    Then home page is not accessible