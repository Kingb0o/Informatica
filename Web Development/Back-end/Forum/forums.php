<?php
    $dbconf = simplexml_load_file("mysql_config_collegedb.xml");
    include("Includes/database.php");
    $stmt = $db->query('SELECT FORUM_ID, FORUM_NAAM FROM Forums WHERE ');

    while($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        echo "<div class=block>";
        echo "<p>" .$row['FORUM_NAAM'] "<p>";
        echo "</div>";
        echo "<div class=block>";
        echo "<p> STUDIENAAM </p>";
        echo "</div>";
        echo "<div class=block>";
        echo  "<a href = "forum.php" class = "forumGa"> Ga naar forum </a>";
        echo "</div>";
    }
?>
