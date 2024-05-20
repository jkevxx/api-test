# USER API REST TEST

This is a simple API REST for a test

> NOTE: Before execute the API you need to install a MySql database, it could work with XAMPP or MySQL Workbench

> In the ```application.properties``` file you can change the database name, user or password according your environment 

---

### Execute the following commands in the terminal 
```bash
cd api_test

./mvnw clean install
```

Execute .jar file:
```bash
cd target

java -jar .\api_test-0.0.1-SNAPSHOT.jar
```

---
### Request Methods
- GET
```md
/api/v1/users?sortedBy=email

get user by Id
/api/v1/users/{id}
```
- POST
```md
/api/v1/users
```
- PATCH
```md
/api/v1/users/{id}
```
- DELETE
```md
/api/v1/users/{id}
```

---
Json for testing:
```json
{
  "email": "user1@mail.com",
  "name": "user1",
  "phone": [ "123", "456" ]
}
```

---
### Docker configuration:
- Build docker image
```bash
docker build . -t apitest
```
- Create container
```bash
docker run -it -p 8080:8080 apitest
```