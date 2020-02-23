# kubernetes-workshop
An introduction into Kubernetes and Service Meshs

## 0 Integration Tests

This module is already built. It proves that your artifacts are working.

Run it with:
```bash
cd integration-tests && mvn clean verify
```

## 1 patient-monolith

This application should be your start. 
Add the following routes to get it working:

```
POST /patients {
  "firstName": "...",
  "lastName": "...",
  "age": 123
}
Returns 201 Created { "id":  "..." }

POST /perscriptions {
  "name": "...",
  "dose": "...",
  "price": 12.12
}
Returns 201 Created { "id": "..." }


GET /patients/<id>/costs

Returns 200 OK { "patientId": "...", "costs": 45.27 }
Returns 404 Not found if patient does not exist

GET /patients 

Returns 200 OK {
  "patientIds": ["id1", "id2"]
}
```

## 2 patient-app
 
Create a new spring-boot-web project including the following dependencies:
1. spring-boot-starter-web
2. spring-boot-starter-actuator
3. spring-boot-starter-data-jpa 

Refactor everything related to the creation of patient object's into this app.

## 3 insurance-app

Create a new spring-boot-web project including the following dependencies:
1. spring-boot-starter-web
2. spring-boot-starter-actuator
3. spring-boot-starter-data-jpa 

Move any patient retrieval and cost calculation into this app.