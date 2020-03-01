# kubernetes-workshop

An introduction into Kubernetes and Service Meshs.

## 1 Branches

The repository splits up into different branches depending on your current progress.

| Task | Branch        | Description                                           |
| ---- | ------------- | ----------------------------------------------------- |
| 1    | master        | Initial skeleton project                              |
| 1    | task1/final   | Implements a monolith to serve all requested routes   |
| 2    | task2/starter | Initial split up into services                        |
| 2    | task2/final   | Implements two services to serve all requested routes |
| 3    | task3/starter | Initial kubernetes files                              |
| 3    | task3/final   | Deploys all services to a k8s cluster                 |

## 2 Tasks

### 2.1 Create a monolith

Build a monolithic application that serves the following aspects:

1) Create new patients
2) Create new prescriptions
3) List all patient ids
4) Calculate the costs of prescriptions for a given patient

You can use the integration tests for validating your artifacts.
In order to continue with the next task more easily, you should 
create different entities for your read and write model.

The task doesn't require any database access - instead you should
persist everything in an in-memory store (hint: singleton).

The directory `patient-monolith` is the only place you need to touch 
for this task.

The following routes have to be served:

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

Note: Do not remove the dependency `spring-boot-starter-actuator`. 
It is used for health checks during integration tests.

Hint: How to create a rest controller

```java
@RestController
@RequestMapping(value = "/myuri")
public class SomeController {
    // ....

    @PostMapping(produces = {"application/json"})
    public ResponseEntity<EntityId> createSth(@RequestBody MyEntity entity) {
        try {
            // ...

            return new ResponseEntity<>(entityId, HttpStatus.CREATED);
        } catch (SomeException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
```

### 2.2 Split into services and containerize them

Split the write and the read concern of your monolithic application into two services and 
create docker containers that are composed inside docker-compose.

Before your start, merge the branch `task2/starter` into your current branch. 
As a result, you should see the following additional artifacts:
1) insurance-service: Service that lists patientIds and calculates prescription costs
2) patient-service: Service that creates patients and prescriptions
3) docker-compose.yml: Composes a postgres database and both services 

As a first step, you should create tables for your views and 
additional materialized view which will fake eventual consistency.
For this purpose have a look into [init.sql](./patient-db/init.sql)
and add the following:

1) Patient Table
2) Prescription Table
3) Dispenses Materialized View
4) PatientIds Materialized View

Hint: You need to add triggers which update the materialized view on
table updates.

In order to solve this task, you have to implement each Dockerfile of both services.
It is important that each service is served on port `8080` - docker-compose will 
remap them to avoid conflicts.

If you have merged the starter branch, you don't have to worry about database access.
Everything is already configured in the `application.yml`:

```yaml
 jpa:
    database: postgresql
    open-in-view: false
  datasource:
    platform: postgres
    url: jdbc:postgresql://patient-database:5432/patientdb
    username: cgmuser
    password: cgmpassword
```

Hint 1: How to create an entity

```java
@Entity
@Table(name = "my_entity", schema = "public")
public class MyEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "column_name")
    private String someProp;
}
```

Hint 2: How to create a Repository

```java
@Repository
public interface SomeRepo extends 
    JpaRepository<MyEntity, String>, 
    JpaSpecificationExecutor<MyEntity> 
{ ... }
```
 
### 2.3 Deploy services into k8s cluster  

TODO

## 3 Integration Tests

This module is already built. It proves that your artifacts are working.

Run it with:
```bash
cd integration-tests && mvn clean verify
```

If you need to configure your tests (e.g. ports have changed), 
then have a look into the following [file](./integration-tests/src/test/resources/config.yml).
It lets you change the host, port, baseUrl and health uri. These are required to run 
the integration tests successfully. 