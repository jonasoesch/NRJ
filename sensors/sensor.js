/**
 * This script simulate a sensor object and send observations to the platform
 */

// Setup
var http = require('http');
var REST_API_HOST = 'localhost';
var REST_API_PORT = 8080;

setInterval(function() {

    // Generate Plug Id
    var plug_id = numberBetween(0,10);

    // Generate kW
    var kW = numberBetween(1,1000);

    var observation = {"kW": 13, "plugId": 5};

    postObservationToPlatform('/NRJ/api/consumptionsObs', observation, function() {
       // Success 
    }, function() {
       // Error 
    });

}, 5000); // every 5 secondes

/**
 * Post an observation to the platform
 * @param endpoint REST API endpoint where to post the observation
 * @param observation observation object
 * @param successCallback function to call if request is successfully completed (can be null)
 * @param errorCallback function to call if an error occurs (can be null)
 */
function postObservationToPlatform(endpoint, observation, successCallback, errorCallback) {

    var post_data = JSON.stringify(observation); // converting the object in string
    
    var options = {
      host: REST_API_HOST,
      port: REST_API_PORT,
      path: endpoint,
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Content-Length': post_data.length
      }
    };

    // Executing the request
    var req = http.request(options, function(res) {
      res.setEncoding('utf-8'); // Encoding in UTF-8

      var responseString = ''; // Request response text will be available here
      res.on('data', function(data) { // When we receive part of the response
        responseString += data; // Response concatenation
      });

      res.on('end', function() { // When the request has been successfully completed
        console.log((new Date()).toJSON() + ' : Observation successfully published <' + endpoint + '>'+JSON.stringify(observation));
        if (successCallback) successCallback.call(null, responseString);
      });
    });

    req.on('error', function(e) { // When the request failed
        console.log((new Date()).toJSON() + ' : Observation publishing failed <' + endpoint + '>');
        if (errorCallback) errorCallback.call();
    });

    req.write(post_data); // writing data
    req.end(); // ending request
}

// Random number generator
function numberBetween(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}
