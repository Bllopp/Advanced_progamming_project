# Authentication Service

This is a Spring application that allow users to register and login with its credentials.


## Application Architecture 

The service uses the Spring Security dependency to manage the login function.\
The register function is handle in our controller.

This service is connected to the MySQL database **auth_service_db**, its schema is described in detail [here](../Database/readme.md)

This application tend to use SOLID principle to assure re-usability, better architecture and readable code.

## Register & Login
- ### `POST /register`

Called when a user want to register in the database.

#### Expect
`Content-Type: application/json`
```json register JSON
{
  "username" : "YourUsername",
  "password" : "YourPassword",
  "email" : "your.address@mail.com",
  "role" : "role"
}
```
#### Result
Add a new entry in the `user` table\
Return a JWT token containing the user's ID and its role.

- ### `POST /login`

Called when a user wants to log in to access to other services.

#### Expect

`Content-Type: application/x-www-form-urlencoded`

| Parameter | Type   | Description      |
|-----------|--------|------------------|
| username  | String | The user's name  |
| password  | String | The user's password |
| email     | String | The user's email |
| role      | String | The user's role |

#### Result
Return a JWT token if the connection succeed with the user's ID and role.
