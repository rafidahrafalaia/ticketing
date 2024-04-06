notes: you can run query in data.sql to generate data for the database to start testing.
# PRD

## Schema Database
![alt text](https://github.com/rafidahrafalaia/ticketing/blob/main/springJwt-main/db_relationship_diagram.png)

## API Documentation

| Method   | URL                                      | Description                              |
| -------- | ---------------------------------------- | ---------------------------------------- |
| `POST`    | `localhost:8080/register`                             | Register User                      |
| `POST`   | `localhost:8080/login`                             | Login User                       |
| `GET`    | `localhost:8080/events`                          | Get All Event                     |
| `GET`  | `localhost:8080/event/{id}`                          | Get Even by Id                |
| `POST`   | `localhost:8080/ticket`                 | Book Ticket                 |
| `GET`    | `localhost:8080/tickets` | Get Tickets by User |

### Register
```http
POST localhost:8080/register
```
Requset Body
```
{
  email: string,
  username: string,
  password: string
}
```
Response
```
{
  message: string,
  token: string
}
```
### Login
```http
POST localhost:8080/login
```
Requset Body
```
{
  username: string,
  password: string
}
```
Response
```
{
  message: string,
  token: string
}
```
### Get All Event
```http
GET localhost:8080/events?page=1&size=10
```
Headers
```
{
  Authorization: Bearer {token}
}
```
Requset Param
```
{
  page(optional): integer,
  size(optional): integer
}
```
Response
```
{
    message: string
    page: integer,
    size: integer,
    data: [
        {
            id: integer,
            name: string,
            description: string,
            organizer: string,
            event_schedule: [
                {
                    id: integer,
                    date: string,
                    time: string,
                    open_to_sell: boolean
                }
            ]
        }
    ]
}

```
### Get Event by Id
```http
GET localhost:8080/event/{id}
```
Headers
```
{
  Authorization: Bearer {token}
}
```
Requset Param
```
{
  id: integer
}
```
Response
```
{
    message: string,
    data: {
            id: integer,
            name: string,
            description: string,
            organizer: string,
            event_schedule: [
                {
                    id: integer,
                    date: string,
                    time: string,
                    open_to_sell: boolean
                }
            ]
        }
    ]
}

```
### Book Ticket
```http
POST localhost:8080/ticket
```
Headers
```
{
  Authorization: Bearer {token}
}
```
Requset Body
```
{
  event_schedule_id: integer
}
```
Response
```
{
    message: string,
    data: {
        id: integer,
        user: {
            email: string,
            username: string
        },
        ticket: {
            code: string,
            event_schedule: {
                id: integer,
                date: string,
                time: string
            }
        }
    }
}

```
### Get Tickets By User
```http
GET localhost:8080/tickets
```
Headers
```
{
  Authorization: Bearer {token}
}
```
Response
```
{
    message: string,
    data: [
        {
            id: integer,
            user: {
                email: string,
                username: string
            },
            ticket: {
                code: string,
                event_schedule: {
                    id: integer,
                    date: string,
                    time: string
                }
            }
        },
    ]
}
```
