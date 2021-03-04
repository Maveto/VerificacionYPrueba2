Feature:

  Scenario: Como usuario
            Quiero crear un item
            Para mantenerme al tanto de lo que representa.

    Given Tengo acceso a Todo.ly

    When Envio una peticion POST a http://todo.ly/api/items.json con el json
    """
    {
      "Content" : "Item",
      "Priority" : 1
    }
    """
    Then Espero que el codigo de respuesta sea 200
    And Espero que el nombre del Item sea "Item"