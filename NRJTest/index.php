<?php

error_reporting(E_ALL);
ini_set('display_errors', 1);

require_once('simpletest/autorun.php');
require_once('simpletest/web_tester.php');
require_once('config.php');

class AllTests extends WebTestCase {
    

    function testRunning() {
        $this->get(URL.'/apartments');
        $this->assertResponse(200);
    }


    //--------
    // Apartment
    // --------
    function testPostApartment() {
        $this->post(
            URL.'/apartments', 
            '{"name": "Appartement 511"}',
            'application/json'
        );
        $this->assertResponse(201);
    }


    //--------
    // Rooms
    // --------
    function testPostRooms() {
        // 2
        $this->post(
            URL.'/rooms', 
            '{
                 "name": "Chambre de Barbie",
                 "apartment": {"apartmentId": 1}
             }',
            'application/json'
        );
        $this->assertResponse(201);
        // 3
        $this->post(
            URL.'/rooms', 
            '{
                 "name": "Cuisine",
                 "apartment": {"apartmentId": 1}
             }',
            'application/json'
        );
        $this->assertResponse(201);
    }


    //--------
    // Plugs
    // --------
    function testPostPlugs() {
        // 4
        $this->post(
            URL.'/plugs', 
            '{
                 "name": "Frigo",
                 "alwayson": true,
                 "room": {"roomId": 2}
             }',
            'application/json'
        );
        $this->assertResponse(201);
        // 5
        $this->post(
            URL.'/plugs', 
            '{
                 "name": "Television",
                 "alwayson": false,
                 "room": {"roomId": 2}
             }',
            'application/json'
        );
        $this->assertResponse(201);
    }

    function testPostPlugToRoom() {
        // 6
        $this->post(
            URL.'/rooms/3/plugs', 
            '{
                 "name": "Lumiere principale",
                 "alwayson": false
             }',
            'application/json'
        );
        $this->assertResponse(201);        
    }


    //--------
    // Observation
    // --------
    function testPostObservation() {
    // 7
    $this->post(
        URL.'/consumptionsObs', 
        '{
        "plug": {
            "plugId": 6
        },
          "timestampMinute":"2014-04-16T14:32:43.07",
          "kW":22.6
        }',
        'application/json'
    );
    $this->assertResponse(201);

    // 8
    $this->post(
        URL.'/consumptionsObs', 
        '{
        "plug": {
            "plugId": 4
        },
          "timestampMinute":"2014-04-16T14:32:43.07",
          "kW":12.6
        }',
        'application/json'
    );
    $this->assertResponse(201);    
    // 9
    $this->post(
        URL.'/consumptionsObs', 
        '{
        "plug": {
            "plugId": 5
        },
          "timestampMinute":"2014-04-16T14:32:43.07",
          "kW":17.0
        }',
        'application/json'
    );
    $this->assertResponse(201);
    // 10 
    $this->post(
        URL.'/consumptionsObs', 
        '{
        "plug": {
            "plugId": 6
        },
          "timestampMinute":"2014-04-16T14:32:43.07",
          "kW":2.9
        }',
        'application/json'
    );
    $this->assertResponse(201);
    }


    function testPostHistory() {
        // 11
        $this->post(
            URL.'/histories', 
            '{
            "plug": {
                "plugId": 6
            },
              "timestampMinute":"2014-04-16T14:32:43.07",
              "status": false
            }',
            'application/json'
        );
        $this->assertResponse(201);
        
        // 12
        $this->post(
            URL.'/histories', 
            '{
            "plug": {
                "plugId": 5
            },
              "timestampMinute":"2014-04-16T14:32:43.07",
              "status": true
            }',
            'application/json'
        );
        $this->assertResponse(201);

    }


    //--------
    // GETs
    // --------
    function testGetApartment() {
        $this->get(URL.'/apartments/1');
        $this->assertResponse(200);
        $this->assertText('"name":"Appartement 511"');
    }

    function testGetRoom() {
        $this->get(URL.'/rooms/2');
        $this->assertResponse(200);
        $this->assertText('"name":"Chambre de Barbie"');
        $this->assertText('"roomId":2');
    }


    function testGetPlug() {
        $this->get(URL.'/plugs/6');
        $this->assertResponse(200);
        $this->assertText('Lumiere principale');
    }

    function testGetPlugsFromRoom() {
        $this->get(URL.'/rooms/2/plugs');
        $this->assertResponse(200);
        $this->assertText('Television');
    }



}


