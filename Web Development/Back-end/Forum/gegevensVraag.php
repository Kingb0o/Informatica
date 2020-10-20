<?php
    $stmt = $db->prepare('SELECT COUNT(ANTWOORD_ID) FROM Antwoorden WHERE VRAAG_ID=?');
    $stmt->bindValue(1, $_GET['id'], PDO::PARAM_STR);
    $stmt->execute();

    $row = $stmt->fetch(PDO::FETCH_ASSOC);

    if($stmt->rowCount() > 0) {
        echo "<p> Aantal antwoorden: " .$row['COUNT(ANTWOORD_ID)']. "</p>";
    }
?>