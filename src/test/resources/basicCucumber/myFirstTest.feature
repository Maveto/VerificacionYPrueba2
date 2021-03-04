Feature: Project

  Scenario: As admin user
            I want to create, update and delete a project
            So that I am able to manipulate the project

    Given I have access to Todo.ly
    When I sent a Post Request to "http://todo.ly/api/pojects.json" with json
    """

    """
    Then I expect a response code 200
    And I expect the project's name to be "Mauri"