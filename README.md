Run the main application (MainApplication.java)

The code uses Java 8 and JUnit 4 for tests
OpenAPI Used:
http://api.openweathermap.org/data/2.5/onecall/timemachine?lat=60.99&lon=30.9&dt=1586468027&appid={API key}

Problem: To find the Max temperature for each of last 3 days

Solution: Use the API to get temperatures on hourly basis for the day and then get the max from the same.
