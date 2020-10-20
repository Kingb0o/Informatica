<?php
    $gebruikersnaam = strip_tags($_POST["gebruikersnaam"]);
    $email   = strip_tags($_POST["email"]);
    $voornaam = strip_tags($_POST["voornaam"]);
    $achternaam   = strip_tags($_POST["achternaam"]);
    $studie   = strip_tags($_POST["studie"]);
    $wachtwoord = strip_tags($_POST["wachtwoord"]);
    $herhaalWachtwoord = strip_tags($_POST["herhaalWachtwoord"]);
    $type = 'INGELOGD';
    $datum = date("y/m/d");
    $biografie = "";

    if (checkWachtwoord() == true) {
        $dbconf = simplexml_load_file("../../mysql_config_collegedb.xml");
        include("../../Includes/database.php");
        $wachtwoord = password_hash($wachtwoord, PASSWORD_DEFAULT);

        $stmt = $db->prepare('INSERT INTO Gebruikers ( GEBRUIKERSNAAM, VOORNAAM, ACHTERNAAM, EMAIL, WACHTWOORD, STUDIE, TYPE, DATUM, BIOGRAFIE) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)');
        $stmt->bindValue(1, $gebruikersnaam, PDO::PARAM_STR);
        $stmt->bindValue(2, $voornaam, PDO::PARAM_STR);
        $stmt->bindValue(3, $achternaam, PDO::PARAM_STR);
        $stmt->bindValue(4, $email, PDO::PARAM_STR);
        $stmt->bindValue(5, $wachtwoord, PDO::PARAM_STR);
        $stmt->bindValue(6, $studie, PDO::PARAM_STR);
        $stmt->bindValue(7, $type, PDO::PARAM_STR);
        $stmt->bindValue(8, $datum, PDO::PARAM_STR);
        $stmt->bindValue(9, $biografie, PDO::PARAM_STR);
        $stmt->execute();

        if($stmt->rowCount() > 0) {
            header("Location: ../../login.php?error=geregistreerd");
        } else {
            header("Location: ../../register.php?error=ingebruik");
        }
    } else if (checkWachtwoord() == false) {
        header("Location: ../../register.php?error=wachtwoordfout");
    }

    function checkWachtwoord() {
        if (strcmp(strip_tags($_POST["wachtwoord"]), strip_tags($_POST["herhaalWachtwoord"])) == 0) {
            return true;
        } else {
            return false;
        }
    }
?>
