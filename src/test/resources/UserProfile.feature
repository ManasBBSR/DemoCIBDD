Feature: Validate user registation on the registration page
  As a CNBC User 
  I should be able to register
  So that I can manage my acccount profile


  Scenario: UserRegistration
    Given user is on CNBC home page
    When user selects register
    And user provides the details and registers
    Then the user should be logged in
    And user signs out

  Scenario Outline: UserProfileUpdate
    Given user is on CNBC home page
    When user selects login
    And provides the login details "old"
    And user updates profile "<firstname>" "<lastname>" "<birthyear>" "<country>" "<gender>" "<industry>" "<occupation>" "<household_income>" "<trades_per_week>" "<broker>"
    And saves the "profile"
    Then "profile" changes are saved
    And user signs out

    Examples: 
      | firstname | lastname | birthyear | country | gender | industry      | occupation | household_income | trades_per_week | broker |
      | cnbc      | user     | 1980      | India   | 0      | Technology/IT | Other      | < $50K           | <20             | 1      |

  Scenario: UserPasswordChange
    Given user is on CNBC home page
    When user selects login
    And provides the login details "old"
    And updates the new password as "Bdd!5678"
    And saves the "password"
    Then "password" changes are saved
    And user signs out

  Scenario: UserAccountDelete
    Given user is on CNBC home page
    When user selects login
    And provides the login details "new"
    And user deletes account
    Then user should be on CNBC home page

  @End
  Scenario: This is just to end the test and perform the cleanup