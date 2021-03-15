Feature: Credentials

  Background: User exists
    Given new user signs up with login "santa" and password "elvesareevil"

  Scenario: User can add, edit and delete credentials

    Given user logs in with login "santa" and password "elvesareevil"
    When user adds credentials for url "udacity.com"
    Then credentials for "udacity.com" url are visible

    Then user logs out

    Given user logs in with login "santa" and password "elvesareevil"
    When user changes the url to "udacity.co.uk"
    Then credentials for "udacity.co.uk" url are visible

    Then user logs out

    Given user logs in with login "santa" and password "elvesareevil"
    When user deletes the credentials
    Then credentials do not exist

