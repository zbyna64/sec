# Final Homework project

## **Endpoints:**

### /auth/register 
* register new user
```java
@RequestBody
{"username": "name",
"password" : "pass"}
```

### /auth/login
* login user
```java
@RequestBody
{"username": "name",
"password" : "pass"}
```
* return JWT token 
