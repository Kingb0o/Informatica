<?php
    $stmt = $db->prepare('SELECT GEBRUIKERSNAAM, DATUM, ONDERWERP, BERICHT FROM Vragen WHERE VRAAG_ID=?');
    $stmt->bindValue(1, $_GET['id'], PDO::PARAM_STR);
    $stmt->execute();

    $row = $stmt->fetch(PDO::FETCH_ASSOC);

    if($stmt->rowCount() > 0) {

        echo "<div class=lightcard>";
        echo "<h2>" .$row['ONDERWERP']. "</h2>";
        echo "<h5> Door " .$row['GEBRUIKERSNAAM']. " op " .$row['DATUM']. "</h5>";
        echo "<p>" .$row['BERICHT']. "</p>";
        echo "</div>";
    }
?>