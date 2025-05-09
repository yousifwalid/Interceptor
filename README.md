# Interceptor
Overview:
----------
This API uses a custom-built **Spring Interceptor** that reads the `User-Agent` header, analyzes it using the `user-agent-utils` library, and determines:

- What kind of device sent the request? (📱 Mobile, 💻 Desktop, or 📟 Tablet)
- What operating system and browser were used?

Depending on the platform, the controller returns customized student data.

Technologies Used:
--------------
- **Java 17**
- **Spring Boot (REST API)**
- **Maven**
- **User-Agent Utils** by [Harald Walker](https://github.com/HaraldWalker/user-agent-utils) **(Archived)**
- **Spring Interceptor** (for pre-processing requests)

 Sample Output:
-----------------
 When request comes from Mobile:
--------------------------------
[

    { "id": 1, "name": "Ahmed - Mobile", "deviceType": "MOBILE" },
  
    { "id": 2, "name": "Mohamed - Mobile", "deviceType": "MOBILE" },
  
    { "id": 3, "name": "Ali - Mobile", "deviceType": "MOBILE" }

]

When request comes from Desktop:
-------------------------------
[

    { "id": 1, "name": "", "deviceType": "COMPUTER" },
  
    { "id": 2, "name": "", "deviceType": "COMPUTER" },
  
    { "id": 3, "name": "", "deviceType": "COMPUTER" }

]

Library Note:
-----------
The library used here (user-agent-utils) is no longer maintained, so I plan to explore modern alternatives in the future such as:

UADetector - Woothee



