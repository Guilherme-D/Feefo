# General observations
* Need to complete the tests

# Feefo Software Engineering Technical Assessment

This project was made using **Java 8** with **Spring**, using the **H2 database** to save data of job titles and **flyway** to automatically run the initial database script.

All project was documented using the **Swagger API**.

When running the project, **flyway will first run the script to create the table of jobs, and populate with the following initial titles**:

>Architect
> 
>Software engineer
> 
>Quantity surveyor
> 
>Accountant

## What it does?

This project normalize the input to a saved job title as following:

| input              | Normalized String   |
|--------------------|---------------------|
| "Java engineer"    | "Software engineer" |
| "C# engineer"      | "Software engineer" |
| "Accountant"       | "Accountant"        |
| "Chief Accountant" | "Accountant"        |

## How to run

After downloading the project, you can click on the "play" button from FeefoApplcation.java or run the following method:

    > mvn spring-boot:run

To run only the tests, use the following command:
    
    > mvn -Dtest=<class> test

## How to use
After run the porject, the routes can be accessed through the swagger interface:

>[http://localhost:8080/swagger-ui.htm/]()

Or using Postman importing the file **postman_collection.json** located at the root folder of this project

## Routes Created
|             | calculate-distance controller |                                    |                                                                 |
|-------------|-------------------------------|------------------------------------|-----------------------------------------------------------------|
| HTTP Method | Route                         | Description                        | Return                                                          |
| POST        | /calculate-distance           | Calculate the Levenshtein distance | The Levenshtein distance, normalizing the value between 0 and 1 |

|             | Job-title controller         |                          |                                                                    |
|-------------|------------------------------|--------------------------|--------------------------------------------------------------------|
| HTTP Method | Route                        | Description              | Return                                                             |
| POST        | /job-title/activate-job      | > Reactivate job title   | Reactivate previous deleted job title                              |
| POST        | /job-title/create-job        | Create new job title     | Save new job title to database to use as normalized                | 
| DELETE      | /job-title/delete-job        | Delete job title         | Delete previous saved job title                                    | 
| GET         | /job-title/normalized-title  | Get the normalized title | Get the normalized title calculated using the Levenshtein distance | 
| GET         | /job-title/search-active-job | Search job titles        | Search paginated list of job titles deleted or not                 |
| PUT         | /job-title/update-job        | Update job title         | Update previous saved job title                                    |



