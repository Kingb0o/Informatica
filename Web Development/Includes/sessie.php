<?php
session_start();
if (isset($_SESSION["gebruikersnaam"])) {

} else {
    header("Location: login.php?error=geenlogin");
}
?>