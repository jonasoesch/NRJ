/**
 * This script create the HTTP server needed by the Android app. It returns a filterable list of facts.
 */
var http = require('http');
var url = require('url');
http.createServer(function (req, res) {
  
  if (req.method == 'GET') { // When receiving a GET HTTP request
  		
  		// Parsing URL
  		var parts = url.parse(req.url, true);
	 	
	 	switch(parts.pathname) {
	 		
	 		/**
			 * HTTP Getting facts
			 * @param q author research
			 */
	 		case '/api/facts':
	 			res.statusCode = 200;
	 			var facts = [
	 				{name: "Fact 1", text: "A first fact", timestamp: (new Date()).getTime()},
	 				{name: "Fact 2", text: "A second fact", timestamp: (new Date()).getTime()},
	 				{name: "Fact 3", text: "A third fact", timestamp: (new Date()).getTime()},
	 				{name: "Fact 4", text: "A fourth fact", timestamp: (new Date()).getTime()},
	 				{name: "Fact 5", text: "A fifth fact", timestamp: (new Date()).getTime()},
	 				{name: "Fact 6", text: "A sixth fact", timestamp: (new Date()).getTime()},
	 				{name: "Fact 7", text: "A seventh fact", timestamp: (new Date()).getTime()},
	 				{name: "Fact 8", text: "A eight fact", timestamp: (new Date()).getTime()},
	 				{name: "Fact 9", text: "A ninth fact", timestamp: (new Date()).getTime()},
	 				{name: "Fact 10", text: "A tenth fact", timestamp: (new Date()).getTime()}
	 			];
	 			var q = parts.query.q;
	 			if (q) { // Filtering the facts list
	 				allFacts = facts;
	 				facts = [];
	 				for (var i in allFacts) {
					  fact = allFacts[i];
					  if(fact.name.indexOf(q) > -1 || fact.text.indexOf(q) > -1) {
						facts.push(fact);
					  }
					}
	 			}
      			return res.end(JSON.stringify(facts));
	 			break;
	 			
	 		case '/':
	 			res.statusCode = 200;
      			return res.end('OK');
	 			break;
	 			
	 		default:
	 			res.statusCode = 404;
      			return res.end('This page does not exists.');
	 			break;
	 	}
	 	
  } else { // Unsupported method 	
  	 // Response to HTTP client
 	 res.writeHead(404, {'Content-Type': 'text/plain'});
 	 res.end();
  }  
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');