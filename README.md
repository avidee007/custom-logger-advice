# Custom Logger Advice Service

**Problem**

Sometimes we need to push request details in a database for tracking purposes,
and this can be an overhead to API performance.
This requires additional handling of database persistence logic which can slow and
cause issues to overall API performance.

**Solution**

* Custom annotation `@LoggerAdvice` to separate logging concern from service/business layer code.

* This annotation has **logToDb** flag which can decide whether logs push logs to database table.

* This annotation uses spring AOP, to do logging as advice to `@LoggerAdvice` annotation.

* When logToDb is set to true it persists the request entity asynchronously to do persistence in
  separate thread to avoid any API performance issues.

**How to use @LoggerAdvice:**

This is method level annotation can be applied to any method to use it.

`@LoggerAdvice
 public void methodWithOutDbLogging() {
   // actual method code logic
}`

`@LoggerAdvice(logToDb = true)
 public void methodWithDbLogging() {
   // actual method code logic
}`

**Custom Logger Advice Service was built with below components**

* spring-web: To build RESTFul api.
* spring-aop: To build logger advice for @LoggerAdvice annotation
* spring-data-jpa: To do persistence of entities in database.
* postreSQL: Database
* Junit and Mockito: For unit testing