Feature: Login as Admin

  Background:
    Given I open Casino page "http://test-app.d6.dev.devcaz.com/admin/login"

  Scenario: Login to admin panel
    Given I enter login name "admin1"
    And I enter login password "[9k<k8^z!+$$GkuP"
    When I click on Sign in button
    Then Admin page "http://test-app.d6.dev.devcaz.com/configurator/dashboard/index" is open
    And User "admin1" is logged successfully
