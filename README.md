---
page_type: sample
languages:
- java
products:
- azure
description: "This is a question service that allows connectivity to MongoDB using azure functions"

---



## Features

Get and Post requests can be made to retreive or publish questions to a database.

## Getting Started

### Prerequisites

This project uses the Maven Wrapper, so Java is necessary. If local testing is required you need to have MongoDB as well.

### Installation

- Clone the project: `git clone https://github.com/math-dojo/question-service.git`
- Build the project: `./mvnw clean package`
-Download MongoDB for local testing: https://www.mongodb.com/download-center/community (preferably install as network service user and with Compass) check that properties file matches config.

### Quickstart

Once the application is built, you can run it locally using the Azure Function Maven plug-in:

`./mvnw azure-functions:run`

And you can test it by publishing a question object and retreiving it by with requests requests to:
 http://localhost:7071/api/question
 question objects have this JSON format 
 {
  "questionTitle": "lorem",
  "questionBody": "lorem",
  "sampleAnswer": "lorem",
  "hints": [
    "lorem"
  ],
  "successRate": 0,
  "difficulty": "easy",
  "solved": false
}

## Deploying to Azure Function

Deploying the application on Azure Function with the Azure Function Maven plug-in:

`./mvnw azure-functions:deploy`

## Question Bank
https://bmos.ukmt.org.uk/home/bmo.shtml
https://www.ukmt.org.uk/competitions/solo/junior-mathematical-challenge/archive

## Notes
Using the word "title" or "body" as Java attributes lead to errors in JSON parsing
