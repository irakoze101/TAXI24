# TAXI24
Set of API For a Sample TAXI Project

## Instructions on Building & Deploying the solution
_Build The solution_
- Ensure to have maven installed on your machine
- Ensure to have jdk 8 or latest installed on your machine.

Execute > mvn clean install from the solution context

_Run the executable_

The above command should build the solution and create a '.jar' file
Navigate into the folder ./target, you will find the '.jar' file

Execute > java -jar Taxi24.jar

## Endpoints
### Base URL: http://localhost:8082/taxi24
- Get a list of all drivers: /drivers
- Get a driver's details by unique identifier: /drivers/get/{id}
- Get a list of drivers in a 3KM Radius : /drivers/search?la=?&lo=?
    Where ? represent the values of the latitude and longitude respectively
- Get a list of all riders: /riders
- Get a rider's details by unique identifier: /riders/{id}
- Get a list of 3 closest drivers: /riders/search/{lat}/{lng}
    Where lat and lng represent the latitude and the longitude respectively
- Create a trip: /trips/create?start_lat=?&start_lng=?&end_lat=?&end_lng=?&passenger_id=?
    Where start_lat and start_lng represent the passenger's latitude and longitude respectively, end_lat and end_lng, the destination coordinates; and passenger_id, the passenger's id, which has to be present.
- Complete a trip: /riders/complete?trip_id=?
    Where trip_id is the trip unique identifier generated upon a trip creation
- List all active trips: /riders/list/PENDING
