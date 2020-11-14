# Getting Started

### About Assignment
In this project I've developed two rest endpoints with help of Spring Boot framework to solve the given problems.
Junit test cases are also implemented for both service operations. 

#### Do we need to use multithreading ? If yes please provide a reason with example.
Multithreading provides luxury of running our code on multiple threads simultaneously which improves throughput and 
responsiveness of the operation. Here in problem 2, I've used multithreading approach to fetch nodes concurrently on 
multiple threads rather than sequentially on a single thread, which improved the response time of the service call to 
a great extent.

1. With multithreading approach in problem 2 response time is 494ms.
![picture](https://github.com/shashnk96/Rest_API/blob/master/img/Img_1_MT.PNG)

2. Without multithreading response time is 3.4sec. 
![picture](https://github.com/shashnk96/Rest_API/blob/master/img/Img_2.PNG)
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.5.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

