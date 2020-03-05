# REST Web Service
## This service provides API for simple CRUD operation for contact resource.
### API has following endpoints:
```
/contacts (GET, POST)
/contacts?name={someName} (GET)
/contacts?phoneNumber={somePhoneNumber} (GET)
/contacts/{id} (GET, PUT, DELETE)
/contacts/{id}/messgae (POST)
```
### You also can check Swagger Documentation here:
```
/swagger-ui.html
```

### This Web Service hosted on AWS using EC2 instance (Ubuntu) and RDS (PostgreSQL)
[Link to the API](http://ec2-35-180-41-25.eu-west-3.compute.amazonaws.com:8090/contacts)
