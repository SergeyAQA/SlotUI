Feature: Open Tables

  Background:
    Given I open Casino page "http://test-app.d6.dev.devcaz.com/admin/login" and login as user "admin1" with password "[9k<k8^z!+$$GkuP"

  Scenario: Open "Players" table via tabs
    Given Admin page "http://test-app.d6.dev.devcaz.com/configurator/dashboard/index" is open
    When I click on "Users" tab
    And I click on "Players" sub-tab
    Then Table "PLAYER MANAGEMENT" is open

  Scenario: Open "Players" table via quick access button
    Given Admin page "http://test-app.d6.dev.devcaz.com/configurator/dashboard/index" is open
    When I click on "Players" quick access button
    Then Table "PLAYER MANAGEMENT" is open
