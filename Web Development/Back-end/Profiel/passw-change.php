<?php
session_start();

if (!empty($_POST['change-passw'])) {
    $dbconf = simplexml_load_file("../../mysql_config_collegedb.xml");
    include("../../Includes/database.php");

    $gebruikersnaam = $_SESSION['gebruikersnaam'];
    $huidig = strip_tags($_POST['huidig']);

    $stmt = $db->prepare("SELECT WACHTWOORD FROM Gebruikers WHERE GEBRUIKERSNAAM = ?");
    $stmt->bindValue(1, $gebruikersnaam, PDO::PARAM_STR);
    $stmt->execute();

    $dbWachtwoord = implode($stmt->fetch(PDO::FETCH_NUM));

    if (password_verify($huidig, $dbWachtwoord) && checkPassword()) {
        $wachtwoord = password_hash(strip_tags($_POST['nieuw']), PASSWORD_DEFAULT);

        $stmt = $db->prepare("UPDATE Gebruikers SET WACHTWOORD = ? WHERE GEBRUIKERSNAAM = ?");
        $stmt->bindValue(1, $wachtwoord, PDO::PARAM_STR);
        $stmt->bindValue(2, $gebruikersnaam, PDO::PARAM_STR);
        $stmt->execute();

        header('Location: ../../profile.php');
    } elseif (!password_verify($huidig, $dbWachtwoord)) {
        header('Location: change-profile.php?error=verkeerd');
    } elseif (!checkPassword()) {
        header('Location: change-profile.php?error=nietgelijk');
    }

} else {
    header('Location: change-profile.php');
    exit();
}

function checkPassword() {
    if (strcmp(strip_tags($_POST['nieuw']), strip_tags($_POST['herhaal'])) == 0) {
        return true;
    } else {
        return false;
    }
}

?>
