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

    function testPostApartment() {
        $this->post(
            URL.'/apartments', 
            '{"name": "Appartement 511"}',
            'application/json'
        );
        $this->assertResponse(201);
    }


    function testPostRooms() {
        // 2
        $this->post(
            URL.'/rooms', 
            '{
                 "name": "Chambre de Barbie",
                 "apartment": {"id": 1}
             }',
            'application/json'
        );
        $this->assertResponse(201);
        // 3
        $this->post(
            URL.'/rooms', 
            '{
                 "name": "Cuisine",
                 "apartment": {"id": 1}
             }',
            'application/json'
        );
        $this->assertResponse(201);
    }


    function testPostPlugs() {
        // 4
        $this->post(
            URL.'/plugs', 
            '{
                 "name": "Frigo",
                 "alwayson": true,
                 "room": {"id": 2}
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
                 "room": {"id": 2}
             }',
            'application/json'
        );
        $this->assertResponse(201);
        // 6
        $this->post(
            URL.'/plugs', 
            '{
                 "name": "Lumière principale",
                 "alwayson": false,
                 "room": {"id": 3}
             }',
            'application/json'
        );
        $this->assertResponse(201);
    }


    function testPostObservation() {
        $this->post(
            URL.'/consumptionsObs', 
            '{
              "timestampMinute": 1397139997864,
              "kW": 12,
              "plugId": 6
            }',
            'application/json'
        );
        $this->assertResponse(200);
    }


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



}


