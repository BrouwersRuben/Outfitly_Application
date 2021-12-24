<img src="src/main/resources/static/images/Outfitly-Logo-Transparent.png" alt="Outfitly logo" width=""/>

## Team members
- Van Boxelaere Roby
- Mika Dominik
- Ušackis Rodžers
- Brouwers Ruben

## How do you run our application
### Database
To make development easy, we chose to work with a H2 database, which is still there. To activate this database you need to
use the profile "H2".

But, because PostgreSQL was a requirement, we used another profile which is used to connect to the PostGresSQL database. This profile is called
"PGSQL".

The instructions to set this up are quite simple:
1. First you need to make a new database in your pgadmin called "_Outfitly_"
2. You have to use the user **postgres** with the password **postgres**
   - if you have a different username and password setup for this database, you can enter those in [this](src/main/java/be/kdg/outfitly/config/PGSQLDatabaseConfig.java) file.
3. Just run the application and everything should work on your machine.
   - If it does not for some reason (it did work on our machines), you just need to use the H2 memory database (Which will still
work perfectly fine, because we are still wiping and reseeding all the tables when the application restarts). 

### How do u use our application
To use our application, please surf to [www.outfitly.com](http://localhost:7070/).

To then navigate to the login page, please press login at the right top of the screen. 
Of course you can always create your own account, but to keep it simple for you, we have already created 2 test accounts. 

the emails and passwords for these accounts are:
- John Doe:
  - testUser1@gmail.com
  - test123
- Bob Shannon
  - testUser2@gmail.com
  - test123

We specifically made 2 users, this way you can test out our multi-user feature. 
To test this out, you do need to use 2 different browsers. While testing we used Google Chrome and Mozilla Firefox.
You have to go to our website on both browsers at the same time, and then in 1 you need to log in with the credentials of Bob Shannon and on the other one you need to use John Doe.
Then you can see that our Java application will display different values for both of them.

## Dependencies we used to make this work

### Data dependencies
* Spring boot starter jpa
  * for our repositories.
* H2 
  * for our H2 memory database.
* PostGreSql
  * for the PostGreSQL database.

### Web dependencies
* Spring boot starter thymeleaf
  * This is the thymeleaf server side template dependency.
* Spring boot starter web
  * This dependency has tomcat, restful, mvc and much more.
* Spring boot starter test
  * This dependency is for the testing frameworks, which we will see in programming 2.3.
* Webjars locator
  * This we use to keep all our libraries upto date, this way handling such a codebase isn't such a pain in the ass (together with Gradle).
* Bootstrap
  * This is a css framework that we used to make our website look half decent.
* Httpclient
  * This we use to fetch all the weather data from the API we are using.
* Passay
  * This we use for password strength checking.

### Security
* Spring boot starter validation
  * This dependency we use for the server side validation .
* Spring boot starter security
  * This dependency makes the multi-user support possible.

### Miscellaneous
* Json
  * Is being used for parsing the json data we get from the api http request.

## Documentation of how we talk with our Arduino
So for this project we chose to use our Arduino as a weather station. 
During our presentation of the tracer bullet we showed that our application could read the sensor data from a small webserver hosted on the Arduino itself.
The Arduino would push new sensor data every 30 minutes in a json format, and our spring application would then read the sensor data every 15 minutes.

This however was not scalable at all, for this to work, the server on which our application was running had to be on the same network as the arduino.
After hours of research we thought that just straight pushing to the database would solve all our problems, the downside to this solution was that our database firewall had
to be disabled. This meant that our database would be  very insecure and could lead to it being hacked. So then we thought of another solution, in this solution we make the Arduino push
its sensor data to a web application hosted by Google(and written in Google Scripts) to be then inserted into a Google sheet. This was a little more secure, because this meant that 
in case of someone with bad intentions wanting to break our system, they could not reach our database, but just the Google Sheet. Only here we had the issue of retrieving all the data 
from the sheet to be used in our application.

Then in the last week, Ruben had a discussion with Mr. Vochten on how to solve this problem. From this discussion came the solution of a very quick and easy rest controller. This also is not
optimal, yet, because we will also only see this material in programming 2.3. Right now the rest controller is open for anyone without any security, so if there was someone with bad intentions who would
like to flood our database with gibberish, then he could. But we will see how we can make this more secure in the second semester.

As of now, our spring application is listening for an url like "http://ipOfLaptop/api/sensordata?hum={double humidity}&temp={double temperature}&em={String email}" in which our arduino will change
the humidity and temperature with the data our sensor outputs and the email with the data the user enters upon configuration of the "WeatherStation" on his phone.

This data will then be checked, to see if it is not testdata from the arduino. If it is not, it will save the data into the database to then be accessed by our Spring application. This data will however not live very long,
because it will be deleted every day at 3AM GMT+1 so that our database does not become to crowded with historical sensor data.

[//]: # (We could also make the arduino light up a led or buzz a buzzer with the status code of the GET request, if needed...)
