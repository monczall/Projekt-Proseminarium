<br><br><br><br><br><br>
<div id="rejestracja">

    <h2>Zarejestruj</h2>

    <form method="post" action="" >

        <label for="imie">Imię:</label><br>
        <input type="text" id="imie" name="imie" value="<?php if($_SERVER["REQUEST_METHOD"] == "POST") echo $_POST['imie'];?>" required><br>

        <label for="nazwisko">Nazwisko:</label><br>
        <input type="text" id="nazwisko" name="nazwisko" value="<?php if($_SERVER["REQUEST_METHOD"] == "POST") echo $_POST['nazwisko'];?>" required><br>

        <label for="email">Email:</label><br>
        <input type="text" id="email" name="email" value="<?php if($_SERVER["REQUEST_METHOD"] == "POST") echo $_POST['email'];?>" required><br>

        <label for="password">Hasło:</label><br>
        <input type="password" id="password" name="password" value="<?php if($_SERVER["REQUEST_METHOD"] == "POST") echo $_POST['password'];?>" required><br>

        <label for="telefon">Telefon:</label><br>
        <input type="number" id="telefon" name="telefon" value="<?php if($_SERVER["REQUEST_METHOD"] == "POST") echo $_POST['telefon'];?>" required><br><br>

        <input type="submit" style="background-color: #fca311;" class="btn_square" name="zarejestruj" value="Zarejestruj">

    </form> 
<?php

if ($_SERVER["REQUEST_METHOD"] == "POST" && $_POST['zarejestruj'] == "Zarejestruj") {
    
    $url = 'http://localhost:3000/api/user/register';
    
    $imie = $_POST['imie'];
    $nazwisko = $_POST['nazwisko'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $telefon = $_POST['telefon'];

    $data = array(
        'imie' => $imie,
        'nazwisko' => $nazwisko,
        'email' => $email,
        'haslo' => $password,
        'telefon' => $telefon,
        'czy_pracownik_warsztatu' => false
    );

    $content = json_encode($data);

    $curl = curl_init($url);

    curl_setopt($curl, CURLOPT_HEADER, false);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($curl, CURLOPT_HTTPHEADER,
    array("Content-type: application/json"));
    curl_setopt($curl, CURLOPT_POST, true);
    curl_setopt($curl, CURLOPT_POSTFIELDS, $content);

    $json_response = curl_exec($curl);

    $status = curl_getinfo($curl, CURLINFO_HTTP_CODE);

    if ( $status != 200 ) {
        echo "Błąd przy tworzeniu użytkownika: $json_response";

    }else{
        curl_close($curl);

        $response = json_decode($json_response, true);
        
        echo "Pomyślnie utworzono użytkownika o adresie email: ";
        echo $response["email"];
        echo '<br><a href="index.php?page=login" style="color: #001845;">Przejdź do logowania</a>';
    }
    
}
?>

</div>

