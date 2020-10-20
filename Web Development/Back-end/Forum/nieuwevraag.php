<?php
    session_start();

    $gebruikersnaam = $_SESSION["gebruikersnaam"];
    $forum_id = "1";
    $datum = date("Y/m/d H:i:s");
    $onderwerp = strip_tags($_POST["onderwerp"]);
    $bericht = strip_tags($_POST["bericht"]);
    
    $dbconf = simplexml_load_file("../../mysql_config_collegedb.xml");
    include("../../Includes/database.php");

    $stmt = $db->prepare('INSERT INTO Vragen (VRAAG_ID, GEBRUIKERSNAAM, FORUM_ID, DATUM, ONDERWERP, BERICHT) VALUES ( ?, ?, ?, ?, ?, ?)');
    $stmt->bindValue(1, $vraag_id, PDO::PARAM_STR);
    $stmt->bindValue(2, $gebruikersnaam, PDO::PARAM_STR);
    $stmt->bindValue(3, $forum_id, PDO::PARAM_STR);
    $stmt->bindValue(4, $datum, PDO::PARAM_STR);
    $stmt->bindValue(5, $onderwerp, PDO::PARAM_STR);
    $stmt->bindValue(6, $bericht, PDO::PARAM_STR);
    $stmt->execute();

    if($stmt->rowCount() > 0) {
        header("Location: ../../forum.php");
        exit;
    } else {
        header("Location: ../../newquestion.php");
    }
?>
