###This api call determines if two cities are connected.

### Show the city list .

[http://localhost:8080/](http://localhost:8080/) 


Example `Boston` and `Newark` _are_ connected:

[http://localhost:8080/connected?origin=Boston&destination=Newark](http://localhost:8080/connected?origin=Boston&destination=Newark) 

Should return 'yes'

Example `Boston` and `Philadelphia` _are_ connected:


[http://localhost:8080/connected?origin=Boston&destination=Philadelphia](http://localhost:8080/connected?origin=Boston&destination=Phildelphia) 

Should return 'yes'

Example `Philadelphia` and `Albany` _are not_ connected:


[http://localhost:8080/connected?origin=Philadelphia&destination=Albany](http://localhost:8080/connected?origin=Philadelphia&destination=Albany)

 Should return 'no'


### Build from source
```bash
    mvn clean install
```
### Run the application

Using maven Spring Boot plugin 
``` 
    mvn spring-boot:run 
```


### Swagger

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)