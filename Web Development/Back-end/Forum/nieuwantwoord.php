<?php
    session_start();

    $gebruikersnaam = $_SESSION["gebruikersnaam"];
    $vraag_id = $_GET['id'];
    $datum = date("Y/m/d H:i:s");
    $bericht = strip_tags($_POST["bericht"]);

    $dbconf = simplexml_load_file("../../mysql_config_collegedb.xml");
    include("../../Includes/database.php");

    $stmt = $db->prepare('INSERT INTO Antwoorden (ANTWOORD_ID, GEBRUIKERSNAAM, VRAAG_ID, DATUM, BERICHT) VALUES ( ?, ?, ?, ?, ?)');
    $stmt->bindValue(1, $antwoord_id, PDO::PARAM_STR);
    $stmt->bindValue(2, $gebruikersnaam, PDO::PARAM_STR);
    $stmt->bindValue(3, $vraag_id, PDO::PARAM_STR);
    $stmt->bindValue(4, $datum, PDO::PARAM_STR);
    $stmt->bindValue(5, $bericht, PDO::PARAM_STR);
    $stmt->execute();

    if($stmt->rowCount() > 0) {
        header("Location: ../../question.php?id=$vraag_id");
        exit;
    } else {
        header("Location: ../../forum.php");
    }
?>
