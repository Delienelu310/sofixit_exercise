Service1:
use
/generate/json/{size}
to genearte array of data object filled with random data as json

/calls/timestamps 	- to get data on recent requests made no service

Service 2:
/v1/csv/{size}		- gets json from service1 in given size and displays it in
			format ‘type, _id, name, type, latitude, longitude’
/v2/csv/{size}?format=your_format - get json from service 1 in given size
	and displays it in given format
/v3/csv/{size}?format=your_format	- gets json from service1 in give size
	and displays it in given format, while calculating the math expressions
	given in it. Math expressions include +, -, *, /, and functions
	pow, sqrt, log10, sin, cos, ln (function can be easily added in code)


/calls/timestamps 	- to get data on recent requests made no service

Service 3:
/test/report/{time}		- launches monitorer, that gathers the cpu and
	memory usage data from services one and two for given time
	meanwhile launches request on service2 on first endpoint with
	sizes 1000, 10000 and 100000

/report/{time}		- to only launch monitorer, that similarly gathers cpu
	and memory usage data, as well as time of request handlers work
/request/{type}/{size}		- to make requests, types taken 0, 1 and 2 
	integers,that correspond to v1, v2 and v3. 
	This request should be made during performance data gathering from previos
	endpoint

React app:
uses 2nd and 3rd endpoints from service3 in order to gather performance data during
	requests made by app user
Page consists of 2 main sectors:
1. Requests manager - here you choose type, size and format of request you want 
	to make to the service2. You can choose multiple requests
2. Monitoring manager - here you can launch monitorer for certain time and then you can
	get a graph with cpu and memory usage. Also you can highlight points
	that correspond to time of certain request 
