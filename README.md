# Spring Session Concurrency Test
Steps to reproduce are:
* Run redis localhost at default port with config:

```
    config set notify-keyspace-events KEA
    config get notify-keyspace-events
```

* Standard Spring Boot 2 Application with Maven, So run 
    class SpringSessionCocurrencyApplication with main
    method(I was using Java 11 on my machine).

* Using Spring Security Header Strategy, So use X-Auth-Token
for session.

* Rest Application, so login by using below curl command:
```
        curl -v -X POST \
          http://localhost:8080/login \
          -H 'Cache-Control: no-cache' \
          -H 'Content-Type: application/json' \
          -d '{
        	
        	"username" : "ankurpathak@live.in",
        	"password" : "password"
        }'
```
* Rest Application, so logout by using below curl command:
```
        curl -v -X GET \
          http://localhost:8080/logout \
          -H 'Cache-Control: no-cache' \
          -H 'X-Auth-Token: 07ea4b64-4944-44c2-a33c-dfc7c1126217'
```
* Rest Application, so access me endpoint by below curl command:
```
    curl -v -X GET \
      http://localhost:8080/me \
      -H 'Cache-Control: no-cache' \
      -H 'X-Auth-Token: e5d5057a-63c6-46ee-9e80-f1442248400e'
```
