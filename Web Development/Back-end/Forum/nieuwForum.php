<?php
    $naam = strip_tags($_POST["naam"]);
    $goedkeuring = '2';

    include("../Includes/database.php");

    echo "Dit werkt";
    $stmt = $db->prepare('INSERT INTO Forums (FORUM_ID, FORUM_NAAM, GOEDKEURING) VALUES ( ?, ?, ?)');
    $stmt->bindValue(1, $forum_id, PDO::PARAM_STR);
    $stmt->bindValue(2, $naam, PDO::PARAM_STR);
    $stmt->bindValue(3, $goedkeuring, PDO::PARAM_STR);
    $stmt->execute();

    if($stmt->rowCount() > 0) {
        echo "Je forum is toegevoegd!";
        header("Location: ../forum.php");
        exit;
    } else {
        header("Location: ../forumHome.php");
        echo "Er is iets foutgegaan.";
    }
?>
