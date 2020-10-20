<?php
    if (isset($_SESSION["gebruikersnaam"])) {
        echo '  <nav class="navbar">
                <a href="index.php"> Home </a>
                <a href="profile.php"> Profiel </a>
                <a href="forumHome.php"> Forum </a>
                <input type="text" placeholder="Zoek.." aria-label="Zoek">
                <a href="Back-end/Login/logout.php" style="float:right"> Logout </a>
                </nav>';
    } else {
        echo '  <nav class="navbar">
                <a href="index.php"> Home </a>
                <a href="profile.php"> Profiel </a>
                <a href="forumHome.php"> Forum </a>
                <input type="text" placeholder="Zoek.." aria-label="Zoek">
                <a href="register.php" style="float:right"> Registreer </a>
                <a href="login.php" style="float:right"> Login </a>
                </nav>';
    }
?>
