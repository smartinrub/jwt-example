# jwt-example

## Get JWT token

url:
http://localhost:8080/token

body:

```json
{
	"email":"email@domain.com",
	"password":"Password1"
}
```

## Use JWT token

url:
http://localhost:8080/index

headers:
 * key: Authorization
 * value: Bearer <jwt_token>
