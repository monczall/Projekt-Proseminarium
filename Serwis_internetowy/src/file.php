<?php
$file = 'naprawson.php';
$json = $_POST["jsonik"];
file_put_contents($file, $json);

$urlPrzelewDoB = 'http://localhost:8080/add';
$curl = curl_init($urlPrzelewDoB);
$json_response = curl_exec($curl);
curl_close($curl);
?>