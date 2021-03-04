Feature:

  Scenario: Como usuario
            Quiero crear, actualizar y borrar un item
            Para tener control sobre los items de la lita.

    Given tengo acceso a Todo.ly
    When Yo envio una peticion POST a http://todo.ly/api/items.json con el json
    """
    {
      "Content" : "Item",
      "Priority" : 3
    }
    """

    Then Yo espero que el codigo de respuesta sea 200
    And Yo espero que el body de la respuesta sea
    """
    {
    "Id": "IGNORE",
    "Content": "Item",
    "ItemType": 1,
    "Checked": false,
    "ProjectId": null,
    "ParentId": null,
    "Path": "",
    "Collapsed": false,
    "DateString": null,
    "DateStringPriority": 0,
    "DueDate": "",
    "Recurrence": null,
    "ItemOrder": null,
    "Priority": 3,
    "LastSyncedDateTime": "IGNORE",
    "Children": [

    ],
    "DueDateTime": null,
    "CreatedDate": "IGNORE",
    "LastCheckedDate": null,
    "LastUpdatedDate": "IGNORE",
    "Deleted": false,
    "Notes": "",
    "InHistory": false,
    "SyncClientCreationId": null,
    "DueTimeSpecified": true,
    "OwnerId": "IGNORE"
    }
    """
    And Yo obtengo el id y lo guardo en Id_Item

    When Yo envio una peticion PUT a http://todo.ly/api/items/Id_Item.json con el json
    """
    {
      "Content" : "Item Update"
    }
    """
    Then Yo quiero que el codigo de respuesta sea 200
    And Espero que el body del response sea
    """
    {
    "Id": "IGNORE",
    "Content": "Item Update",
    "ItemType": 1,
    "Checked": false,
    "ProjectId": null,
    "ParentId": null,
    "Path": "",
    "Collapsed": false,
    "DateString": null,
    "DateStringPriority": 0,
    "DueDate": "",
    "Recurrence": null,
    "ItemOrder": null,
    "Priority": 3,
    "LastSyncedDateTime": "IGNORE",
    "Children": [

    ],
    "DueDateTime": null,
    "CreatedDate": "IGNORE",
    "LastCheckedDate": null,
    "LastUpdatedDate": "IGNORE",
    "Deleted": false,
    "Notes": "",
    "InHistory": false,
    "SyncClientCreationId": null,
    "DueTimeSpecified": true,
    "OwnerId": "IGNORE"
    }
    """

    When Yo envio una peticion DELETE http://todo.ly/api/items/Id_Item.json

    Then Yo espero que el codigo de respuesta sea 200

    And Yo espero que el body de la respuesta sea
    """
    {
    "Id": "IGNORE",
    "Content": "Item Update",
    "ItemType": 1,
    "Checked": false,
    "ProjectId": null,
    "ParentId": null,
    "Path": "",
    "Collapsed": false,
    "DateString": null,
    "DateStringPriority": 0,
    "DueDate": "",
    "Recurrence": null,
    "ItemOrder": null,
    "Priority": 3,
    "LastSyncedDateTime": "IGNORE",
    "Children": [

    ],
    "DueDateTime": null,
    "CreatedDate": "IGNORE",
    "LastCheckedDate": null,
    "LastUpdatedDate": "IGNORE",
    "Deleted": true,
    "Notes": "",
    "InHistory": false,
    "SyncClientCreationId": null,
    "DueTimeSpecified": true,
    "OwnerId": "IGNORE"
    }
    """

