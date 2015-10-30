# SOAP2REST
This APM SOAP gateway exposes metrics as a REST webservice

# Usage

java -cp RESTgtw-0.0.1-SNAPSHOT.jar com.ca.field.test.Main 
	-e/-endpoint introscope-webservice
	-u/-user EM userid
	-p/-password password
	optional -port portnumber (default=8310)

example:
java -cp RESTgtw-0.0.1-SNAPSHOT.jar com.ca.field.test.Main -e "http://localhost:8081/introscope-web-services/services/MetricsDataService" -u "user" -p "password"

# REST usage

GET
http://rest-host:port/RESTgtw/rest?interval=INTERVAL&agent=AGENTEXPRESSION&metric=METRICEXPRESSION(&end=INTERVALEND)

example: 
http://localhost:8310/RESTgtw/rest?interval=7200&agent=(.*)|(.*)|(.*)&metric=TEST(.*):(.*)
http://localhost:8310/RESTgtw/rest?interval=7200&agent=(.*)|(.*)|(.*)&metric=TEST(.*):(.*)&end=29 10 2015 18:00:00

POST (application/json)
http://localhost:8310/RESTgtw/rest
{
         "agent": "AGENTEXPRESSION",
         "metric": METRICEXPRESSION,
         "interval" : INTERVAL,
         ("end": INTERVALEND)
}

example:
{
         "agent": "(.*)|(.*)|(.*)",
         "metric": "Frontends(.*):(.*)",
         "interval" : 7200,
         "end": "19 10 2015 18:00:00"
}

# 5 minute video on the application
https://catechnologies.webex.com/catechnologies/ldr.php?RCID=ae32b7b77779f80bdc6f7edec20ff36d 
