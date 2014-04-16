<?php

error_reporting(E_ALL);
ini_set('display_errors', 1);

require_once('simpletest/autorun.php');
require_once('simpletest/web_tester.php');

define("URL", "http://localhost:8080/NRJ/api");

class AllTests extends WebTestCase {
    
    public $u = "http://localhost:8080/NRJ/api";

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
                 "name": "Télévision",
                 "alwayson": false,
                 "room": {"roomId": 2}
             }',
            'application/json'
        );
        $this->assertResponse(201);
        // 6
        /*
        $this->post(
            URL.'/rooms/3/plugs', 
            '{
                 "name": "Lumière principale",
                 "alwayson": false
             }',
            'application/json'
        );
        $this->assertResponse(201);
         */
    }


    //--------
    // Observation
    // --------
    function testPostObservation() {
    // 7
    $this->post(
        URL.'/consumptionsObs', 
        '{
          "timestampMinute": 1397139997864,
          "kW": 12,
          "plugId": 5
        }',
        'application/json'
    );
    $this->assertResponse(200);

    // 8
    $this->post(
        URL.'/consumptionsObs', 
        '{
          "timestampMinute": 1398139997864,
          "kW": 6,
          "plugId": 6
        }',
        'application/json'
    );
    $this->assertResponse(200);    
    // 9
    $this->post(
        URL.'/consumptionsObs', 
        '{
          "timestampMinute": 1398139997864,
          "kW": 7,
          "plugId": 6
        }',
        'application/json'
    );
    $this->assertResponse(200);
    // 10 
    $this->post(
        URL.'/consumptionsObs', 
        '{
          "timestampMinute": 1408139997864,
          "kW": 9,
          "plugId": 5
        }',
        'application/json'
    );
    $this->assertResponse(200);
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
        $this->assertText('"name":""kW": 12"');
    }

    function testGetPlugsFromRoom() {
        $this->get(URL.'/rooms/2/plugs');
        $this->assertResponse(200);
        $this->assertText('Télévision');
    }



}


