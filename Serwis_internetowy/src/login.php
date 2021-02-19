<br><br><br><br><br><br>
<div id="logowanie">

    <h2>Zaloguj</h2>

    <form method="post" action="" >

        <label for="email">Email:</label><br>
        <input type="text" id="email" name="email" required><br>

        <label for="password">Hasło:</label><br>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" style="background-color: #008CBA;" class="btn_square" name="zaloguj" value="Zaloguj">

    </form> 
    <?php

if ($_SERVER["REQUEST_METHOD"] == "POST" && $_POST['zaloguj'] == "Zaloguj") {

    $_SESSION["zalogowany"] = true;
    $_SESSION["id"] = "60077a19b46f4d0568ca8711";
    $_SESSION["imie"] = "Łukasz";
    $_SESSION["nazwisko"] = "Mączka";
    $_SESSION["email"] = "test@gmail.com";
    $_SESSION["telefon"] = "123456789";
    echo "Pomyślnie zalogowano";
    //echo $json_response;

    header('Location: index.php?page=main');

    /*
    $email = $_POST['email'];
    $password = $_POST['password'];

    $url = 'http://localhost:3000/api/user/login';
    
    $data = array(
        'email' => $email,
        'haslo' => $password
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
        if($json_response){
            echo "Błąd przy logowaniu:<br> $json_response";
        }else{
            echo "Błąd przy logowaniu:<br> Brak połączenia z bazą";
        }
        
    }else{
        curl_close($curl);

        $response = json_decode($json_response, true);
        $_SESSION["zalogowany"] = true;
        $_SESSION["id"] = $response['_id'];
        $_SESSION["imie"] = $response['imie'];
        $_SESSION["nazwisko"] = $response['nazwisko'];
        $_SESSION["email"] = $response['email'];
        $_SESSION["telefon"] = $response['telefon'];
        echo "Pomyślnie zalogowano";
        //echo $json_response;
    
        header('Location: index.php?page=main');
    }
    */
}


?>
</div>

<!--index.php?page=login -->