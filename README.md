# todo-app

How to start the todo application
---

1. Run `mvn clean package` to build your application
1. Start application with `java -jar target/app-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

Functionality
---

1. To create todo task:
```shell script
$ http localhost:8080/tasks name='buy some milk'
POST /tasks HTTP/1.1
Accept: application/json, */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Content-Length: 25
Content-Type: application/json
Host: localhost:8080
User-Agent: HTTPie/2.0.0

{
    "name": "buy some milk"
}

HTTP/1.1 200 OK
Content-Length: 70
Content-Type: application/json
Date: Sat, 21 Mar 2020 13:09:29 GMT

{
    "createdAt": 1584796169275,
    "done": false,
    "id": 1,
    "name": "buy some milk"
}
```

2. Get particular task by id:
```shell script
$ http localhost:8080/tasks/1
GET /tasks/1 HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:8080
User-Agent: HTTPie/2.0.0



HTTP/1.1 200 OK
Content-Length: 70
Content-Type: application/json
Date: Sat, 21 Mar 2020 13:12:36 GMT
Vary: Accept-Encoding

{
    "createdAt": 1584796169275,
    "done": false,
    "id": 1,
    "name": "buy some milk"
}
```

3. To list all the tasks:
```shell script
$ http localhost:8080/tasks
GET /tasks HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:8080
User-Agent: HTTPie/2.0.0



HTTP/1.1 200 OK
Content-Length: 217
Content-Type: application/json
Date: Sat, 21 Mar 2020 13:13:18 GMT
Vary: Accept-Encoding

[
    {
        "createdAt": 1584796169275,
        "done": false,
        "id": 1,
        "name": "buy some milk"
    },
    {
        "createdAt": 1584796231420,
        "done": false,
        "id": 2,
        "name": "walk out a dog"
    },
    {
        "createdAt": 1584796272536,
        "done": false,
        "id": 3,
        "name": "wash the dishes"
    }
]
```

4. Mark particular task as done
```shell script
$ http localhost:8080/tasks/1 done=true                                                                                                                                                   130 ↵
POST /tasks/1 HTTP/1.1
Accept: application/json, */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Content-Length: 16
Content-Type: application/json
Host: localhost:8080
User-Agent: HTTPie/2.0.0

{
    "done": "true"
}

HTTP/1.1 200 OK
Content-Length: 69
Content-Type: application/json
Date: Sat, 21 Mar 2020 13:14:04 GMT

{
    "createdAt": 1584796169275,
    "done": true,
    "id": 1,
    "name": "buy some milk"
}
```

5. Remove task completely
```shell script
$ http delete localhost:8080/tasks/1
DELETE /tasks/1 HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Content-Length: 0
Host: localhost:8080
User-Agent: HTTPie/2.0.0



HTTP/1.1 200 OK
Content-Length: 69
Content-Type: application/json
Date: Sat, 21 Mar 2020 13:15:10 GMT

{
    "createdAt": 1584796169275,
    "done": true,
    "id": 1,
    "name": "buy some milk"
}

```