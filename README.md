# Getting Started

### About - Date Time Dimension Application

For any Data Warehouse or Business Intelligence application, one needs to build or have a Date / Time dimension to allow 
the users to analyse their facts based on one or many attributes of a Date or Time period. 

This is a **Spring Boot Application** and I have developed this as part of building conformed dimensions in another 
Business Intelligence application. 

### Pre-requisite

* Java 8

### Configuration Options

This package provides configuration options to customize the way in which data gets generated for Date and 
Time dimension. See the application.properties file to understand the various config tokens available. 

`Date Dimension Configuration options`

In case of Date dimension you could provide a start date and end date, so that the package would populate the data for 
that given period

```java
# date in YYYY-MM-DD format
start.date=2015-02-26
end.date=2100-03-03
```

Some countries have start day of their week as MONDAY or SATURDAY or SUNDAY. Use the below token to provide any of 
these values

```java
start.day.of.week=MONDAY
```

*Note:* 

In general weekdays are considered as 5 days, from and including the specified day in weekday.starts.on token.
In general weekends are considered as 2 days from and including the specified day in weekend.starts.on token.

```java
weekday.starts.on=MONDAY
weekend.starts.on=SATURDAY
```

In general a fiscal calendar involves a period of 12 months, that an organization uses to report its accounting 
details. It starts at the beginning of a quarter, such as January 1, April 1, July 1, or October 1 based on the choice 
of the entity involved. The entity can be a corporate, government, or nonprofit.

```java
# fiscal calendar parameters
fiscal.calendar.start.month=APRIL
```

Some project requires Time dimension to be at `Seconds` precision whereas for some project it is enough to have at 
`Minute` precision. So make use of the following config token in application.properties file

```java
# Allowed values for time.dimension.precision token : minute, second
time.dimension.precision=minute
```

###Database Configuration

By default, the application.properties have the config done to use H2 Database. On executing the 
application jar using the command provided in the section [Execution Options](#execution-options), the data will get 
populated in `DIM_DATE` and `DIM_TIME` tables.

You could access the H2 Database console in your browser using the url given below

```http request
<Replace IP and Port address as per your setup>

http://localhost:8080/h2-console/
```

For convenience, I have packaged the application jar with the following Database drivers

- H2 
- Postgres 
- MySQL
- Microsoft SQL Server
- Oracle
- DB2

Just configure the jdbc driver properties and you are good to go

###Download
* You could use the jar - datetime-dimension-<version>.jar - available as part of this repository in
`build/libs`
* You could use the application.properties in `config` directory as a reference config file  

###Execution Options

- As a first step, configure the application.properties with your desired values
- For the ease of usage, this application gets initialized and populates the Date and Time dimension table on executing 
the the Spring boot jar

```java
java -jar <application jar name> --spring.config.location="<JAR_LOCATION_DIRECTORY>\config\application.properties"
``` 
- For some reason, if you want to truncate a table and repopulate the data again on a specific dimension table, you 
could make use of the rest api as well without restarting the application 

```java
POST Requests
Change the IP and Port details as per your setup

http://localhost:8080/date
http://localhost:8080/time
```

### 
  

### Builds
You can clone the github repository and build the jar from source code using gradle

```shell script
cd <Project Directory>
gradlew bootJar
```

### Contribution
You can contribute to this Repository in any form. Please contact me for approval. 