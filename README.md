# Final Homework project

## **Endpoints:**

### POST /auth/register 
* register new user
```java
@RequestBody
{"username": "name",
"password" : "pass"}
```

### POST /auth/login
* login user
```java
@RequestBody
{"username": "name",
"password" : "pass"}
```
* return JWT token 

### GET /user/balance
* get user balance

### POST /user/balance
* update user balance
```java
@RequestBody
{"amount": 1000.0}
```

### GET /items
* list all not sold items by page (default size is 5 items per page)

### GET /items/{id}
* list item with details by item ID

### POST /items
* crete new item
```java
@RequestBody
{"name": "tv",
"description": "cool tv",
"startingPrice": 100.0,
"purchasePrice": 200.0}
```

### POST /items/{id}
* bid at the item by ID
```java
@RequestBody
{"bidPrice": 1000.0}
```
