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

#Example response
{"data":[{"WIN-QNKJ766473R|EPAgentProcess|EPAgent":{"TEST|TESS Agent|TIM|localhost|Business Service|Service 1":{"Average Response Time (ms)":"1","Concurrent Invocations":"2","Errors Per Interval":"3","Responses Per Interval":"4","Stall Count":"5","Total Defects Per Interval":"7","Total Transactions Per Interval":"6","Utilization % (process)":"8"}}},{"WIN-QNKJ766473R|EPAgentProcess|EPAgent":{"TEST|TESS Agent|TIM|localhost|Business Service|Service 2":{"Average Response Time (ms)":"10","Concurrent Invocations":"20","Errors Per Interval":"30","Responses Per Interval":"40","Stall Count":"50","Total Defects Per Interval":"70","Total Transactions Per Interval":"60","Utilization % (process)":"80"}}},{"WIN-QNKJ766473R|EPAgentProcess|EPAgent":{"TEST|TESS Agent|TIM|localhost|Business Service|Service 3":{"Average Response Time (ms)":"100","Concurrent Invocations":"200","Errors Per Interval":"300","Responses Per Interval":"400","Stall Count":"500","Total Defects Per Interval":"700","Total Transactions Per Interval":"600","Utilization % (process)":"800"}}},{"WIN-QNKJ766473R|EPAgentProcess|EPAgent":{"TEST|TESS Agent|TIM|localhost|Business Service|Service 4":{"Average Response Time (ms)":"1000","Concurrent Invocations":"2000","Errors Per Interval":"3000","Responses Per Interval":"4000","Stall Count":"5000","Total Defects Per Interval":"7000","Total Transactions Per Interval":"6000","Utilization % (process)":"8000"}}}],"end":"Fri Oct 30 08:55:15 CET 2015","start":"Fri Oct 30 06:55:15 CET 2015"}

# 5 minute video on the application
https://catechnologies.webex.com/catechnologies/ldr.php?RCID=ae32b7b77779f80bdc6f7edec20ff36d 
