Feature: Sort Tables

  Background:
    Given I open Casino page "http://test-app.d6.dev.devcaz.com/admin/login" and login as user "admin1" with password "[9k<k8^z!+$$GkuP"

  Scenario: Sort "Players" table by registration date ascending
    Given Admin page "http://test-app.d6.dev.devcaz.com/configurator/dashboard/index" is open
    When I click on "Players" quick access button
    And I click on column header "Registration date" to sort "asc"
    Then Table sorted by date "asc"
