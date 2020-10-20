<?php
    $stmt = $db->prepare('SELECT GEBRUIKERSNAAM, DATUM,BERICHT FROM Antwoorden WHERE VRAAG_ID=?');
    $stmt->bindValue(1, $_GET['id'], PDO::PARAM_STR);
    $stmt->execute();

    if ($stmt->rowCount() > 0) {
      while($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        echo "<div class=darkcard>";
        echo "<h4>" .$row['GEBRUIKERSNAAM']. " schreef op " .$row['DATUM']. "</h4>";
        echo "<p>" .$row['BERICHT']. "</p>";
        echo "</div>";
    }
}
?>