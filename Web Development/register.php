<!DOCTYPE html>
<html>

<?php
session_start();
if (isset($_SESSION["gebruikersnaam"])) {

} else {
    header("Location: profile.php?change=ingelogd");
}
?>

<head>
    <?php include("Includes/style.php");?>
</head>

<body>
    <?php include("Includes/navbar.php");?>

    <div class="login-plaatje">
        <div class="titel-border">
            <h1 class="titel-p">Registreer</h1>
        </div>
    </div>

    <form action="Back-end/registreer.php" method="post">
        <div class="login">
            <table class="informatietabel">
                <tr>
                    <p style="font-weight: normal;">
                        Vul de informatie hieronder in om een account te maken.<br>
                    </p>
                </tr>
                <?php
                    if (isset($_GET['error'])) {

                        if($_GET['error'] == "wachtwoordfout") {
                            echo "Het wachtwoord is fout. Probeer het opnieuw.";
                        }

                        if ($_GET['error'] == "ingebruik") {
                            echo "De gebruikersnaam is al ingebruik. Probeer een andere gebruikersnaam.";
                        }
                    }
                ?>
                <tr>
                    <th>
                        <label for="gebruikersnaam">Gebruikersnaam</label>
                    </th>
                    <td>
                        <input class="invoer" type="text" name="gebruikersnaam" required>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="wachtwoord">Wachtwoord</label>
                    </th>
                    <td>
                        <input class="invoer" type="password" name="wachtwoord" required>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="herhaalWachtwoord">Herhaal Wachtwoord</label>
                    </th>
                    <td>
                        <input class="invoer" type="password" name="herhaalWachtwoord" required>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="email">E-mailadres</label>
                    </th>
                    <td>
                        <input class="invoer" type="email" name="email" required>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="voornaam">Voornaam</label>
                    </th>
                    <td>
                        <input class="invoer" type="text" name="voornaam" required>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="achternaam">Achternaam</label>
                    </th>
                    <td>
                        <input class="invoer" type="text" name="achternaam" required>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="studie">Studie</label>
                    </th>
                    <td>
                        <select class="invoer" name="studie" required>
                            <option value="informatica">Informatica</option>
                            <option value="kunstmatige intelligentie">Kunstmatige Intelligentie</option>
                            <option value="informatiekunde">Informatiekunde</option>
                        </select>
                    </td>
                </tr>
            </table>
            <input class="opslaan" type="submit" value="Registreer">
        </div>
    </form>

    <?php include("Includes/footer.php");?>

    </body>
</html>