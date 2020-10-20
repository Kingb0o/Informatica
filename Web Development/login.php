<!doctype html>
<html lang="en">

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
                    <h1 class="titel-p">Login</h1>
                </div>
    </div>

        <form action="Back-end/Login/loginSessie.php" method="post">
            <div class="login">
                <table class="informatietabel">
                <?php
                    if (isset($_GET['error'])) {
                        if($_GET['error'] == "wachtwoordfout") {
                            echo "Het wachtwoord is fout. Probeer het opnieuw.";
                        }

                        if ($_GET['error'] == "databasefout") {
                            echo "Deze gebruikersnaam bestaat niet.";
                        }

                        if ($_GET['error'] == "geenlogin") {
                            echo "U bent niet ingelogd. Probeer in te loggen of te registreren.";
                        }

                        if ($_GET['error'] == "geregistreerd") {
                            echo "U bent zojuist geregistreerd. Probeer in te loggen.";
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
                </table>
                <input class="opslaan" type="submit" value="Login">
            </div>
        </form>

<?php include("Includes/footer.php");?>

</body>
</html>
