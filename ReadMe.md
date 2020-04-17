
# Ecommerce <img src="https://cdn.pixabay.com/photo/2016/09/30/17/22/ecommerce-1705786_1280.png" width="50" height="50"> <img src="https://image.flaticon.com/icons/svg/544/544564.svg" width="50" height="50"> <img src="https://cdn.pixabay.com/photo/2016/09/30/17/29/shopping-1705800_1280.png" width="50" height="50">
  
Ecommerce is a mechanics for a web application made with Java 8 using the REST interface designed to support consumers in online shopping. 
This project is a part of the Bootcamp: Java Plus Program at Kodilla.

## Requirements

[Java Development Kit (8 or higher)](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)

[MySQL](https://www.mysql.com/)
## Installation

-[x] Create a directory for the project and cd into it.

-[x] Clone down this repository:

```bash
git clone https://github.com/metho/project-jdp-2003-03.git
```
-[x] After dowloading the code to your local repository, build the project:

```bash
gradlew build
```
-[x] Create database in MySQL and user according to the data stored in the file [application.properties](https://github.com/metho/project-jdp-2003-03/blob/master/src/main/resources/application.properties):

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/kodilla_project?serverTimezone=Europe/Warsaw&useSSL=False
spring.datasource.username=kodilla_user
spring.datasource.password=kodilla_password
```

...and that's it! Now you can run the project on your IDE and enjoy the functionalities that it gives you ;)


## Endpoints

### **Product Group**
Products available in the shop have been divided into groups.

####![#00FF00](https://placehold.it/15/00FF00/000000?text=+) GET
*Method used to get all the product groups. No parameters required.*
#####*Model*
- id (integer, optional),
- name (string, optional)
#####*Request URL*
```bash
http://localhost:8080/v1/group
```
#####*curl*
```bash
curl -X GET --header 'Accept: */*' 'http://localhost:8080/v1/group'
```
#####*Example response*
```bash
[
  {
    "id": 0,
    "name": "string"
  }
]
```
#####*Response Messages*
| Code          | Explanation   |
| ------------- | ------------- |
| 200           | Success       |
| 401           | Unauthorized  |
| 403           | Forbidden     |
| 404           | Not Found     |

#### ![#00FF00](https://placehold.it/15/00FF00/000000?text=+) GET
*Method used to get chosen product group. Parameter required.*
#####*Parameter type*
- Long
#####*Request URL*
```bash
http://localhost:8080/v1/group/{id}
```
#####*curl*
```bash
curl -X GET --header 'Accept: text/plain' 'http://localhost:8080/v1/group/{id}'
```
#####*Example response*
```bash
[
  {
    "id": 0,
    "name": "string"
  }
]
```
#####*Response Messages*
| Code          | Explanation   |
| ------------- | ------------- |
| 200           | Success       |
| 401           | Unauthorized  |
| 403           | Forbidden     |
| 404           | Not Found     |

#### ![#c5f015](https://placehold.it/15/c5f015/000000?text=+) POST
*Method used to create new product group. Parameter required*
#####*Parameter content type*
- json

#####*Request URL*
```bash
http://localhost:8080/v1/group
```
#####*curl*
```bash
curl -X POST --header 'Content-Type: application/json' --header 'Accept: text/plain' -d '{ \ 
   "id": 0, \ 
   "name": "string" \ 
 }' 'http://localhost:8080/v1/group'
```
#####*Example request body*
```bash
 {
   "id": 0,
   "name": "string"
 }
```
#####*Response Messages*
| Code          | Explanation   |
| ------------- | ------------- |
| 200           | Success       |
| 401           | Unauthorized  |
| 403           | Forbidden     |
| 404           | Not Found     |

####![#1589F0](https://placehold.it/15/1589F0/000000?text=+) PUT
*Method used to update product group. Parameter required.*
#####*Parameter content type*
- json
#####*Request URL*
```bash
http://localhost:8080/v1/group
```
#####*curl*
```bash
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: text/plain' -d '{ \ 
   "id": 0, \ 
   "name": "string" \ 
 }' 'http://localhost:8080/v1/group'
```
#####*Example request body*
```bash
  {
    "id": 0,
    "name": "string"
  }
```
#####*Response Messages*
| Code          | Explanation   |
| ------------- | ------------- |
| 201           | Created      |
| 401           | Unauthorized  |
| 403           | Forbidden     |
| 404           | Not Found     |

####![#f03c15](https://placehold.it/15/f03c15/000000?text=+) DELETE
*Method used to delete chosen product group. Parameter required.*
#####*Parameter type*
- Long
#####*Request URL*
```bash
http://localhost:8080/v1/group/{id}
```
#####*curl*
```bash
curl -X DELETE --header 'Accept: text/plain' 'http://localhost:8080/v1/group/{id}'
```

#####*Response Messages*
| Code          | Explanation   |
| ------------- | ------------- |
| 200           | Success       |
| 401           | Unauthorized  |
| 403           | Forbidden     |
| 404           | Not Found     |

### Product
### Cart
User is filling up cart with requested products.
### Order
### User
User can register and set up own account. Each account is secured with a password.
#### ![#00FF00](https://placehold.it/15/00FF00/000000?text=+) GET
*Method used to get all users. No parameters required.*
#####*Model*
- address (string, optional),
- blocked (boolean, optional),
- id (integer, optional),
- name (string, optional),
- password (string, optional)
#####*Request URL*
```bash
http://localhost:8080/v1/user
```
#####*curl*
```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/v1/user'
```
#####*Example response*
```bash
[
  {
     "id": 1,
        "name": "user1",
        "password": "$2aonhK4V5.",
        "blocked": false,
        "address": "address1"
  }
]
```
#####*Response Messages*
| Code          | Explanation   |
| ------------- | ------------- |
| 200           | Success       |
| 401           | Unauthorized  |
| 403           | Forbidden     |
| 404           | Not Found     |

#### ![#00FF00](https://placehold.it/15/00FF00/000000?text=+) GET
*Method used to get chosen user. Parameter required.*
#####*Parameter type*
- Long
#####*Request URL*
```bash
http://localhost:8080/v1/user/{id}
```
#####*curl*
```bash
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/v1/user/{id}'
```
#####*Example response*
```bash
  {
    "id": 1,
      "name": "user1",
      "password": "$XonhK4V5.",
      "blocked": false,
      "address": "address1"
  }
```
#####*Response Messages*
| Code          | Explanation   |
| ------------- | ------------- |
| 200           | Success       |
| 401           | Unauthorized  |
| 403           | Forbidden     |
| 404           | Not Found     |

####![#c5f015](https://placehold.it/15/c5f015/000000?text=+) POST
*Method used to create new user. Parameter required*
#####*Parameter content type*
- json

#####*Request URL*
```bash
http://localhost:8080/v1/user
```
#####*curl*
```bash
curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' -d '{ \ 
   "address": "string", \ 
   "blocked": true, \ 
   "id": 0, \ 
   "name": "string", \ 
   "password": "string" \ 
 }' 'http://localhost:8080/v1/user'
```
#####*Example request body*
```bash
 {
   "address": "string",
     "blocked": true,
     "id": 0,
     "name": "string",
     "password": "string"
 }
```
#####*Response Messages*
| Code          | Explanation   |
| ------------- | ------------- |
| 200           | Success       |
| 401           | Unauthorized  |
| 403           | Forbidden     |
| 404           | Not Found     |

####![#1589F0](https://placehold.it/15/1589F0/000000?text=+) PUT
*Method used to update user data. Parameter required.*
#####*Parameter content type*
- json
#####*Request URL*
```bash
http://localhost:8080/v1/user
```
#####*curl*
```bash
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \ 
   "address": "string", \ 
   "blocked": true, \ 
   "id": 0, \ 
   "name": "string", \ 
   "password": "string" \ 
 }' 'http://localhost:8080/v1/user'
```
#####*Example request body*
```bash
  {
     "address": "string",
      "blocked": true,
      "id": 0,
      "name": "string",
      "password": "string"
  }
```
#####*Response Messages*
| Code          | Explanation   |
| ------------- | ------------- |
| 201           | Created      |
| 401           | Unauthorized  |
| 403           | Forbidden     |
| 404           | Not Found     |

#### ![#f03c15](https://placehold.it/15/f03c15/000000?text=+) DELETE
*Method used to delete chosen user. Parameter required.*
#####*Parameter type*
- Long
#####*Request URL*
```bash
http://localhost:8080/v1/user/{id}
```
#####*curl*
```bash
curl -X DELETE --header 'Accept: text/plain' 'http://localhost:8080/v1/user/{id}'
```

#####*Response Messages*
| Code          | Explanation   |
| ------------- | ------------- |
| 200           | Success       |
| 401           | Unauthorized  |
| 403           | Forbidden     |
| 404           | Not Found     |
| 204           | No content    |


## Usage

## Troubleshooting

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.


