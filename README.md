# jwt-example

## Usage Example
### Get JWT token

```shell
curl -H "Content-Type: application/json" -X POST -d '{"email":"email@domain.com","password":"Password1"}' http://localhost:8080/token
```

### Use JWT token

```shell
curl -H "Authorization: Bearer <jwt_token>" -X GET http://localhost:8080/index
```

*jwt token only valid for 30 seconds*
