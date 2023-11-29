# Task API Spec

## Create Task

Endpoint : POST /api/tasks

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "title" : "Make Jurnal",
  "description" : "Make a jurnal 3 page",
  "status" : "pending"
}
```

Response Body (Success) : 

```json
{
  "data": {
    "id" : 1,
    "title": "Make Jurnal",
    "description": "Make a jurnal 3 page",
    "status": "pending"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Invalid request"
}
```

## Update Task

Endpoint : PUT /api/tasks/{idTask}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "title" : "Make Jurnal",
  "description" : "Make a jurnal 3 page",
  "status" : "complete"
}
```

Response Body (Success) :

```json
{
  "data": {
    "id" : 1,
    "title": "Make Jurnal",
    "description": "Make a jurnal 3 page",
    "status": "pending"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Invalid request"
}
```

## Get Task

Endpoint : GET /api/tasks/{idTask}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": {
    "id" : 1,
    "title": "Make Jurnal",
    "description": "Make a jurnal 3 page",
    "status": "in progress"
  }
}
```

Response Body (Failed, 404) :

```json
{
  "errors" : "Task is not found"
}
```

## Search Task

Endpoint : GET /api/tasks

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data": [
    {
      "id" : 1,
      "title": "Make Jurnal",
      "description": "Make a jurnal 3 page",
      "status": "in progress"
    },
    {
      "id" : 2,
      "title": "Make Power Point",
      "description": "Make a Power Point",
      "status": "complete"
    }
  ]
}
```

Response Body (Failed) :

```json
{
  "errors" : "Unauthorized"
}
```

## Remove Task

Endpoint : DELETE /api/tasks/{idTask}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "OK"
}
```

Response Body (Failed) :

```json
{
  "errors" : "Task is not found"
}
```
