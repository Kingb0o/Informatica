<?php

$gebruikersnaam = strip_tags($_POST["gebruikersnaam"]);
$wachtwoord = strip_tags($_POST["wachtwoord"]);
$dbconf = simplexml_load_file("../../mysql_config_collegedb.xml");

include("../../Includes/database.php");

$stmt = $db->prepare("SELECT WACHTWOORD FROM `Gebruikers` WHERE GEBRUIKERSNAAM = ?");
$stmt->bindValue(1, $gebruikersnaam, PDO::PARAM_STR);
$stmt->execute();

if ($stmt->rowCount() > 0) {
    $dbWachtwoord = implode($stmt->fetch(PDO::FETCH_NUM));

    if (password_verify($wachtwoord, $dbWachtwoord)) {
        session_start();
        $_SESSION['gebruikersnaam'] = $gebruikersnaam;
        header("Location: ../../index.php");
    } else {
        header("Location: ../../login.php?error=wachtwoordfout");
    }
} else {
    header("Location: ../../login.php?error=databasefout");
}
?>