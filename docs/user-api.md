# User API

### Register
Endpoint : POST /api/users

Request Body :

```json
{
  "username" : "fitriarien",
  "password" : "rahasia",
  "name" : "Arine Fitriani" 
}
```

Response Body (Success) :

```json
{
  "data" : {
    "username" : "fitriarien",
    "name" : "Arine Fitriani"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Username must not blank, ???"
}
```

### Login

Endpoint : POST api/auth/login

Request Body :

```json
{
  "username" : "fitriarien",
  "password" : "rahasia"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "username" : "fitriarien",
    "name" : "Arine Fitriani",
    "token" : "token"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Username and password does not match"
}
```

### Logout

Endpoint : POST api/auth/logout

Header : Authorization "Bearer token"

Response Body (Success) :

```json
{
  "message" : "Success"
}
```
