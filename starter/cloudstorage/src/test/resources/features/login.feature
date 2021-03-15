Feature: Users can register and login

  Scenario:   Home page not accessible without login

    When user navigates to home page
    Then home page is not accessible

  Scenario:    Home page accessible after logging in

    Given new user signs up with login "firstUser" and password "sillyPassword"
    When user logs in with login "firstUser" and password "sillyPassword"
    Then home page is accessible
    When  user logs out
    Then home page is not accessible

