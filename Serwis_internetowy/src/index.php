<?php
session_start();
?>
<!DOCTYPE HTML>
<html lang="pl">
<head>   
	<meta charset="utf-8" />
 	<title>Twój serwis z warsztatami!</title>	  
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> 
    <!------------------------------BEZ TEJ LINII NIE DZIAŁA SKALOWANIE NA URZĄDZENIACH MOBILNYCH-------->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--------------------------CZCIONKI--------------------->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
    <!--------------------------CSS--------------------->
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">     
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> 
    <link href="https://unpkg.com/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://use.fontawesome.com/releases/v5.12.1/css/all.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/core/main.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/daygrid/main.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/4.2.0/bootstrap/main.min.css" rel="stylesheet">
</head>    
<body>
    <div id="header">
        <a href="index.php">
            <div id="logo">
                <span id="text_title">Znajdź warsztat</span><br>
                <span id="text_under_title">Twoj serwis z warsztatami</span> 
            </div>
        </a>

        <div id="login">
            <?php
                if(isset($_SESSION["zalogowany"])==true){
                    if($_SESSION["zalogowany"]==true){
                        ?>
                            Zalogowano jako: <?php echo $_SESSION["email"]; ?>
                            
                            <form style="display: inline-block" method="post">
                                <button type="submit" style="background-color: #fca311;" class="btn_square" name="naprawy" class="btn logout_btn">Moje naprawy</button>
                            </form>

                            <form style="display: inline-block" action="" method="post">
                                <button type="submit" style="background-color: #008CBA;" class="btn_square" name="logout" class="btn logout_btn">Wyloguj się</button>
                            </form>

                        <?php
                    }
                }else{
                    echo 'You are not logged in ';
                    echo '<a href="index.php?page=login"><button style="background-color: #008CBA;" class="btn_square" type="button">Login</button></a>';
                    echo '<a href="index.php?page=register"><button style="background-color: #fca311;" class="btn_square" type="button">Register</button></a>';
                }
            ?>
        </div>
        
    </div>

    <div id="container"  style="min-height: 1100px;">
        <?php

        if(isset($_SESSION["zalogowany"])==true){
            if($_SESSION["zalogowany"]==true){
                if(isset($_POST['naprawy'])){
                    $current_page = 'naprawy';
                }else if(isset($_GET['page'])){
                    $current_page = $_GET['page'];
                }else{
                    $current_page = 'home';
                }


                //$current_page = isset($_GET['page']) ? $_GET['page'] : null;
                //$current_page = 'home';
            }
        }else{
            if(isset($_GET['page'])){
                if($_GET['page'] == 'register'){
                    $current_page = 'register';
                }else{
                    $current_page = 'login';
                }
            }else{
                $current_page = 'login';
            }
            //$current_page = 'login';
            //$current_page = isset($_GET['page']) ? $_GET['page'] : null;
        }
        	

		switch ($current_page) 
		{
            case 'login':
				include 'login.php';
				break;

            case 'register':
                include 'register.php';
                break;

            case 'logout':
                include 'logout.php';
                break;

            case 'home':
                default:
                include 'home.php';
                break;

            case 'naprawy':
                include 'naprawy.php';
                break;
            case 'kalendarz':
                include 'kalendarz.php';
                break;
            case 'kalendarz2':
                include 'kalendarz2.php';
                break;
        }
        ?>
    </div>

    <div id="footer">
        Grupa: Warsztaty samochodowe.<br> 
        Łukasz Mączka, Adam Młynek, Damian Paluch
    </div>

</body>    
</html>
<?php
if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST['logout']))
{
    func();
}
function func()
{
    session_destroy();
    redirect('index.php?page=login');
    exit;    
}
function redirect($url)
{
    if (!headers_sent())
    {    
        header('Location: '.$url);
        exit;
    }
    else
    {  
        echo '<script type="text/javascript">';
        echo 'window.location.href="'.$url.'";';
        echo '</script>';
        echo '<noscript>';
        echo '<meta http-equiv="refresh" content="0;url='.$url.'" />';
        echo '</noscript>'; exit;
    }
}
?>