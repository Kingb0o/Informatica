<?php
    $dbconf = simplexml_load_file("mysql_config_collegedb.xml");
    include("Includes/database.php");

    $stmt = $db->query('SELECT VRAAG_ID, GEBRUIKERSNAAM, DATUM, ONDERWERP, BERICHT FROM Vragen WHERE FORUM_ID=1');
    while($row = $stmt->fetch(PDO::FETCH_ASSOC)) {

    $vraag_id = $row['VRAAG_ID'];
    echo "<div class=lightcard>";
    echo "<h2>" .$row['ONDERWERP']. "</h2>";
    echo "<h5> Door " .$row['GEBRUIKERSNAAM']. " op " .$row['DATUM']. "</h5>";
    echo "<p>" .$row['BERICHT']. "</p>";
    echo "<a href=question.php?id=$vraag_id> Bekijk alle antwoorden </a>";
    echo "</div>";
}
?>
