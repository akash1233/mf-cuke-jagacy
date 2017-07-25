Feature: Mainframe Testing through jagacy


  Scenario: Connect to homedepot mainframe system
    Given I start a new emulator session
    When I login and type tso
    And Enter my login id
    Then I should be able to see login screen


  Scenario: Search faculty phone number using name
    Given I start a new emulator session
    When I open phonbook application
    And search for faculty name "PRATIK"
    Then I should see the results matching with my search criteria
      | PRATIK KRISHNAN             UNAVAILABLE  MARY KAY O'CONNOR PROCESS    3122 |
