# Product API

### Create Product
Endpoint : POST /api/products

Header : Authorization "Bearer token"

Request Body :

```json
{
  "image_path" : "www.example.com",
  "product_name" : "laptop A",
  "selling_price" : 15000000,
  "buying_price" : 13000000
}
```

Response Body (Success) :
```json
{
  "data" : {
    "id" : "UUID-random-1",
    "image_path" : "www.example.com",
    "product_name" : "laptop A",
    "selling_price" : 15000000.0,
    "buying_price" : 13000000.0
  }
}
```

Response Body (Failed, 401) :
```json
{
  "errors" : "Unauthorize"
}
```

### Get Products
Endpoint : GET /api/products

Header : Authorization "Bearer token"

Response Body (Success) :
```json
{
  "data" : [
    {
      "id" : "UUID-random-1",
      "image_path" : "www.example.com",
      "product_name" : "laptop A",
      "selling_price" : 15000000.0,
      "buying_price" : 13000000.0
    },
    {
      "id" : "UUID-random-2",
      "image_path" : "www.example2.com",
      "product_name" : "handphone A",
      "selling_price" : 8000000.0,
      "buying_price" : 6500000.0
    }
  ]
}
```

Response Body (Failed, 401) :
```json
{
  "errors" : "Unauthorize"
}
```
Response Body (Failed, 403) :
```json
{
  "errors" : "Not Found"
}
```

### Get Single Product
Endpoint : GET /api/products/{productId}

Header : Authorization "Bearer token"

Response Body (Success) :
```json
{
  "data" : {
    "id" : "UUID-random-1",
    "image_path" : "www.example.com",
    "product_name" : "laptop A",
    "selling_price" : 15000000.0,
    "buying_price" : 13000000.0
  }
}
```

Response Body (Failed, 401) :
```json
{
  "errors" : "Unauthorize"
}
```
Response Body (Failed, 403) :
```json
{
  "errors" : "Not Found"
}
```

### Update Product
Endpoint : PUT /api/products/{productId}

Header : Authorization "Bearer token"

Request Body :

```json
{
  "image_path" : "www.example3.com",
  "product_name" : "laptop baru",
  "selling_price" : 17000000,
  "buying_price" : 14000000
}
```

Response Body (Success) :
```json
{
  "data" : {
    "id" : "UUID-random-1",
    "image_path" : "www.example3.com",
    "product_name" : "laptop baru",
    "selling_price" : 17000000.0,
    "buying_price" : 14000000.0
  }
}
```

Response Body (Failed, 401) :
```json
{
  "errors" : "Unauthorize"
}
```
Response Body (Failed, 403) :
```json
{
  "errors" : "Not Found"
}
```

### Delete Single Product
Endpoint : DELETE /api/products/{productId}

Header : Authorization "Bearer token"

Response Body (Success) :
```json
{
  "data" : "Deleted"
}
```

Response Body (Failed, 401) :
```json
{
  "errors" : "Unauthorize"
}
```
Response Body (Failed, 403) :
```json
{
  "errors" : "Not Found"
}
```