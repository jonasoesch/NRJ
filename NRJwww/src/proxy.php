<?php
$thing = $_GET['thing'];
$id = $_GET['id'];
if ($id != '') {
    define('URL_WS', "http://localhost:8080/NRJ/api/".$thing."/".$id);
} else {
    define('URL_WS', "http://localhost:8080/NRJ/api/".$thing);
}
header('content-type: application/json');
echo file_get_contents(URL_WS);