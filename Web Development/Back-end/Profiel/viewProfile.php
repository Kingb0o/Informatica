<?php
    $dbconf = simplexml_load_file("mysql_config_collegedb.xml");
    include("Includes/database.php");

    $gebruikersnaam = $_SESSION['gebruikersnaam'];
    $stmt = $db->prepare("SELECT * FROM `Gebruikers` WHERE GEBRUIKERSNAAM = ?");
    $stmt->bindValue(1, $gebruikersnaam, PDO::PARAM_STR);
    $stmt->execute();

    $gegevens = $stmt->fetch(PDO::FETCH_NUM);

    if ($stmt->rowCount() > 0) {
        echo '  <div class="profiel">
                    <table class="informatietabel">
                        <tr>
                            <th>Voornaam</th>
                            <td>'.$gegevens[1].'</td>
                        </tr>
                        <tr>
                            <th>Achternaam</th>
                            <td>'.$gegevens[2].'</td>
                        </tr>
                        <tr>
                            <th>Gebruikersnaam</th>
                            <td>'.$gegevens[0].'</td>
                        </tr>
                        <tr>
                            <th>Studie</th>
                            <td>'.$gegevens[5].'</td>
                        </tr>
                        <tr>
                            <th>Aantal geplaatste berichten</th>
                            <td></td>
                        </tr>
                        <tr>
                            <th>Datum van aanmaak</th>
                            <td>'.$gegevens[7].'</td>
                        </tr>
                        <tr>
                        <th>Biografie</th>
                        <td>'.$gegevens[8].'</td>
                    </tr>
                    </table>
                    <br>
                    <div>
                        <a href="change-profile.php"><button class="info-knop">Informatie wijzigen</button></a>
                    </div>
                </div>';
    } else {
        echo "Er ging iets fout met het ophalen van je gegevens.";
    }
?>
