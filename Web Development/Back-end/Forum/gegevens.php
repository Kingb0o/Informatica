<?php
    $stmt = $db->query('SELECT COUNT(VRAAG_ID) FROM Vragen WHERE FORUM_ID=1');
    $row = $stmt->fetch(PDO::FETCH_ASSOC);

    echo "<p> Aantal vragen: " .$row['COUNT(VRAAG_ID)']. "</p>";

    $stmt = $db->query('SELECT COUNT(GEBRUIKERSNAAM) FROM Gebruikers');
    $row = $stmt->fetch(PDO::FETCH_ASSOC);

    echo "<p> Aantal gebruikers: " .$row['COUNT(GEBRUIKERSNAAM)']. "</p>";
?>
