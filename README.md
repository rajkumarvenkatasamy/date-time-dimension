# Getting Started

### About - Date Time Dimension Application

For any Data Warehouse or Business Intelligence application, one needs to build or have a Date / Time dimension to allow 
the users to analyse their facts based on one or many attributes of a Date or Time period. 

This is a **Spring Boot Application** and I have developed this as part of building conformed dimensions in another 
Business Intelligence application. 

### Pre-requisite

* Download and Install : Java 8

### QuickStart Guide

My goal is to make a non java person also to use this application and get the data required for Date and Time 
dimension tables.  

1. Download the jar - datetime-dimension-<version>.jar - available as part of this repository in
  `build/libs` in your machine
2. For the ease of usage, this application gets initialized and populates the Date and Time dimension tables on executing 
the the Spring boot jar

    ```java
    java -jar <application jar name> --spring.config.location="<JAR_LOCATION_DIRECTORY>\config\application.properties"
    ``` 
3. Open the below url in browser to access the default in-built h2 database
    ```http request
    <Replace IP and Port address as per your setup>
    
    http://localhost:8080/h2-console/
    ```
4. Login as user "sa" and password as "password"
5. Execute the sql commands 
    ```sql
    select * from dim_date;
    select * from dim_time;
    ```
6. To export the data as CSV file, execute the below command in the same sql command windows

```sql
call CSVWRITE ( '<Directory path>/dim_date.csv', 'SELECT * FROM dim_date' );
call CSVWRITE ( '<Directory path>/dim_time.csv', 'SELECT * FROM dim_time' ); 
```
That's it, you have date and time dimension data in both Table and CSV format

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
- For best performance, deploy the jar in the database server and execute it
- For the ease of usage, this application gets initialized and populates the Date and Time dimension tables on executing 
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

### Key Data Points

This is an optional section for reading. You skip it at your will.

- All key fields (Except : Quarter Key and Half Year Key) points to the corresponding period since epoch 
(1970-01-01 : YYYY-MM-DD format).
<br>
`For Instance:` 
If the input start date is 1970-01-01, then the date key is 0 and gets incremented or decremented by one from that date. 
So negative values are possible in key fields based on the date which gets processed. This is ok and expected behavior.

- The purpose of key field is to use in aggregate tables based on the grain level of the aggregates.
<br>
`For Instance:`
<br>
If the aggregate grain level is week, then use week_key in aggregate 
<br>
If the aggregate grain level is quarter, then use quarter_key in aggregate


### Builds
You can clone the github repository and build the jar from source code using gradle

```shell script
cd <Project Directory>
gradlew bootJar
```

### Contribution
You can contribute to this Repository in any form. Please contact me for approval. 