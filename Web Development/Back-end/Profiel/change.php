<?php
session_start();

if (isset($_POST['change-info'])) {
    $dbconf = simplexml_load_file("../../mysql_config_collegedb.xml");
    include("../../Includes/database.php");

    $gebruikersnaam = $_SESSION['gebruikersnaam'];
    $voornaam = strip_tags($_POST['voornaam']);
    $achternaam = strip_tags($_POST['achternaam']);
    $email = strip_tags($_POST['email']);
    $studie = strip_tags($_POST['studie']);
    $bio = strip_tags($_POST['bio']);

    $stmt = $db->prepare('UPDATE Gebruikers SET VOORNAAM = ?, ACHTERNAAM = ?, EMAIL = ?, STUDIE = ?, BIOGRAFIE = ? WHERE GEBRUIKERSNAAM = ?');
    $stmt->bindValue(1, $voornaam, PDO::PARAM_STR);
    $stmt->bindValue(2, $achternaam, PDO::PARAM_STR);
    $stmt->bindValue(3, $email, PDO::PARAM_STR);
    $stmt->bindValue(4, $studie, PDO::PARAM_STR);
    $stmt->bindValue(5, $bio, PDO::PARAM_STR);
    $stmt->bindValue(6, $gebruikersnaam, PDO::PARAM_STR);
    $stmt->execute();

    header('Location: ../../profile.php?change=gewijzigd');

} else {
    header('Location: change-profile.php?change=foutwijzig');
    exit();
}