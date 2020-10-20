<?php
session_start();
$dbconf = simplexml_load_file("mysql_config_collegedb.xml");
include('Includes/database.php');

$gebruikersnaam = strip_tags($_SESSION['gebruikersnaam']);
$stmt = $db->prepare("SELECT * FROM `Gebruikers` WHERE GEBRUIKERSNAAM = ?");
$stmt->bindValue(1, $gebruikersnaam, PDO::PARAM_STR);
$stmt->execute();

$gegevens = $stmt->fetch(PDO::FETCH_NUM);

echo '
    <form action="Back-end/Profiel/change.php" method="post">
        <div class="info-wijzigen">
            <table class="informatietabel">
                <tr>
                    <th>
                        <label for="voornaam">Voornaam</label>
                    </th>
                    <td>
                        <input class="invoer" type="text" name="voornaam" value="'.$gegevens[1].'">
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="achternaam">Achternaam</label>
                    </th>
                    <td>
                        <input class="invoer" type="text" name="achternaam" value="'.$gegevens[2].'">
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="studie">Studie</label>
                    </th>
                    <td>
                        <select class="invoer" name="studie" value="'.$gegevens[5].'">
                            <option value="informatica">Informatica</option>
                            <option value="kunstmatige intelligentie">Kunstmatige Intelligentie</option>
                            <option value="informatiekunde">Informatiekunde</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="email">E-mailadres</label>
                    </th>
                    <td>
                        <input class="invoer" type="email" name="email" value="'.$gegevens[3].'">
                    </td>
                </tr>
                <tr class="bio-rij">
                    <th>
                        <label for="bio">Biografie</label>
                    </th>
                    <td class="bio-td">
                        <textarea class="bio" rows="4" name="bio">'.$gegevens[8].'</textarea>
                    </td>
                </tr>
            </table>
            <input class="opslaan" type="submit" name="change-info" value="Wijzigingen opslaan">
        </div>
    </form>

       <form action="Back-end/Profiel/passw-change.php" method="post">
        <div class="info-wijzigen">
            <table class="informatietabel">
                <tr>
                    <th>
                        <label for="huidig">Huidig wachtwoord</label>
                    </th>
                    <td>
                        <input class="invoer" type="password" name="huidig" required>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="nieuw">Nieuw wachtwoord</label>
                    </th>
                    <td>
                        <input class="invoer" type="password" name="nieuw" required>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="herhaal">Herhaal nieuw wachtwoord</label>
                    </th>
                    <td>
                        <input class="invoer" type="password" name="herhaal" required>
                    </td>
                </tr>
            </table>
            <input class="opslaan" type="submit" name="change-passw" value="Wachtwoord veranderen">
        </div>
    </form>
';
