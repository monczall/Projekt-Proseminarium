<span style="color: #33415C;">.</span>
<div id="main">
    <?php
        if(!isset($_POST['rozpoczęto']) && !isset($_POST['wybrano_miasto']) && !isset($_POST['wyslano_opis']) ){
    ?>
        <h1 style="color: #fca311; font-weight: bolder;"> Tylko krok dzieli Cię od wyboru wyspecjalizowanego warsztatu </h1>
        <form method="post" id="rozpocznij">
            <input type="submit" name="rozpoczęto" class="btn_square" style="background-color: #fca311" value="Rozpocznij wyszukiwanie warsztatu">
            </form>
    <?php
        }
        if($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['rozpoczęto']) == "Rozpocznij wyszukiwanie warsztatu"){
    ?>
        <form method="post" id="form_miasto">
            Wybierz miasto<br>
            <select id="miasto" name="miasto" form="form_miasto">
                <option value="Rzeszów">Rzeszów</option>
            </select>
            <br><input type="submit" name="wybrano_miasto" style="background-color: #fca311" class="btn_square" value="Przejdź dalej">
        </form>
    <?php
        }
           if($_SERVER["REQUEST_METHOD"] == "POST" && (isset($_POST['wybrano_miasto']) == "Przejdź dalej" || isset($_POST['wybrano_miasto']) == "Powrót")){
            $miasto = $_POST['miasto'];
            $_SESSION["miasto"] = $miasto;
    ?>

        <form method="post" id="form_dane_o_samochodzie">
            Podaj markę samochodu<br>
            <select id="marka" name="marka_samochodu" form="form_dane_o_samochodzie">
                <option value="Alfa Romeo">Alfa Romeo</option>
                <option value="Aston Martin">Aston Martin</option>
                <option value="Audi">Audi</option>
                <option value="BMW">BMW</option>
                <option value="Bugatti">Bugatti</option>
                <option value="Cadillac">Cadillac</option>
                <option value="Chevrolet">Chevrolet</option>
                <option value="Chrysler">Chrysler</option>
                <option value="Citroen">Citroen</option>
                <option value="Cupra">Cupra</option>
                <option value="Dacia">Dacia</option>
                <option value="Doge">Doge</option>
                <option value="Ferrari">Ferrari</option>
                <option value="Fiat">Fiat</option>
                <option value="Ford">Ford</option>
                <option value="FSO">FSO</option>
                <option value="GMC">GMC</option>
                <option value="Hyundai">Hyundai</option>
                <option value="Jaguar">Jaguar</option>
                <option value="Jeep">Jeep</option>
            </select>
            <br>Podaj model samochodu<br>
            <input type="text" name="model_samochodu" value="" required>
            <br>Wybierz rok produkcji<br>
            <input type="number" name="rok_produkcji" min="1900" max="2099" step="1" value="2021" />
            <br>Opisz usterkę<br>
            <textarea name="opis_usterki"> </textarea>
            <br><input type="submit" name="wyslano_opis" class="btn_square" style="background-color: #fca311" value="Przejdź dalej">
            <input type="hidden" name="wybrane_miasto" value="<?php echo $miasto;?>">
        </form>
    <?php
        }
        if($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['wyslano_opis']) == "Przejdź dalej"){

            $_SESSION["marka_samochodu"] = $_POST['marka_samochodu'];
            $_SESSION["model_samochodu"] = $_POST['model_samochodu'];
            $_SESSION["rok_produkcji"] = $_POST['rok_produkcji'];
            $_SESSION["opis_usterki"] = $_POST['opis_usterki'];

            /* ZMIENNE SESYJNE DO WYSWIETLANIA
            echo "<br>Wyszukiwanie warsztatów dla podanych danych: ";

            echo "<br><br>Miasto: " . $_SESSION["miasto"];
            echo "<br>Marka samochodu: " . $_SESSION["marka_samochodu"];
            echo "<br>Model samochodu: " . $_SESSION["model_samochodu"];
            echo "<br>Rok produkcji: " . $_SESSION["rok_produkcji"];
            echo "<br>Opis usterki: " . $_SESSION["opis_usterki"];

            echo "<br><br>Zalogowany: " . $_SESSION["zalogowany"];
            echo "<br>ID: " . $_SESSION["id"];
            echo "<br>Imie: " . $_SESSION["imie"];
            echo "<br>Nazwisko: " . $_SESSION["nazwisko"];
            echo "<br>Email: " . $_SESSION["email"];
            echo "<br>Telefon: " . $_SESSION["telefon"];
            */

            ?>

        <div class="card-deck">
            <div class="card border-success mb-3" style="color: black; border-width:5px;">
                <img src="img/pexels-cottonbro-4488637.jpg" class="card-img-top border-success" alt="warsztat1">
                <div class="card-body">
                <h5 class="card-title">Warsztat u Zdzisia</h5>
                <p class="card-text">Otwarty: Pon-Sob,
                    <br> Od: 6:00 Do: 18:30</p>
                </div>
                <div class="card-footer">
                <small class="text-muted">Polecany warsztat
                    <form method="post" action="index.php?page=kalendarz">
                        <input type="hidden" name="warsztat" value="warsztat2">
                        <br><input type="submit" name="zarezerwuj_termin" style="background-color: #fca311" class="btn_square" value="Zarezerwuj termin">
                    </form>
                </small>
                </div>
            </div>

            <div class="card" style="color: black; border-width:5px;; border-color: grey;">
                <img src="img/pexels-erik-mclean-4732635.jpg" class="card-img-top" alt="warsztat2">
                <div class="card-body">
                <h5 class="card-title">Warsztat u Władzia</h5>
                <p class="card-text">Otwarty: Przez cały tydzień,
                    <br> 24h</p>
                </div>
                <div class="card-footer">
                <small class="text-muted">Zweryfikowany warsztat
                    <form method="post" action="index.php?page=kalendarz2">
                        <input type="hidden" name="warsztat" value="warsztat2">
                        <br><input type="submit" name="zarezerwuj_termin" style="background-color: #fca311" class="btn_square" value="Zarezerwuj termin">
                    </form>
                </small>
                </div>
            </div>
        </div>

            <?php
        }
    ?>
</div>
