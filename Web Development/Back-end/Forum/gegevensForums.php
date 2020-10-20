<?php
    include("Includes/database2.php");
    $stmt = $db->query('SELECT COUNT(FORUM_ID) FROM Forums');
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
    echo "<p> Aantal forums: " .$row['COUNT(FORUM_ID)']. "</p>";
?>