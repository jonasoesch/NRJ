<?php
$thing = $_GET['thing'];
$id = $_GET['id'];

//define('URL_WS', "http://localhost:8080/NRJ/api/".$thing."/".$id);
define('URL_WS', "http://localhost:8080/NRJ/api/rooms);
header('content-type: application/json');
echo file_get_contents(URL_WS);