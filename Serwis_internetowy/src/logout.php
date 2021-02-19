<?php
$_SESSION["zalogowany"]=false;
session_unset();
session_destroy();
header('Location: index.php?page=login');
?>
<div id="some_alert">
    Pomyślnie wylogowano.. 
    Nastąpi
</div>

