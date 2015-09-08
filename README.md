# University Registrar

##### _{university sim app}, {Sept 2015}_

#### By **Kallen Millner, Ashley Sullins & Stephany Garcia**

## Description

{This app lets the user create a program where they can add students, courses, and place students into those courses vice versa.}

## Setup

* Set up the database in PostgreSQL by running the following commands in your terminal:
```
  psql
  CREATE DATABASE database_name;
  \c database_name;
  CREATE TABLE table_name (id serial PRIMARY KEY, attribute varchar);
```
* If you wish to run tests, create a test database:
```
  CREATE DATABASE database_name_test WITH TEMPLATE database_name;
```
* Clone this repository.
* Using the command line, navigate to the top level of the cloned directory.
* Make sure you have gradle installed. Then run the following command in your terminal:
```
  gradle run
```
* Go to localhost:4567.
* Go!

## Technologies Used

* Java
* PostgreSQL
* Spark
* Velocity
* Gradle
* JUnit
* FluentLenium

### Legal

Copyright (c) 2015 **Kallen Millner, Ashley Sullins & Stephany Garcia**

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
