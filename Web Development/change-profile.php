<!DOCTYPE html>
<html>
    <head>
        <?php include("Includes/style.php");?>
    </head>

    <body>
    <?php include("Includes/navbar.php"); ?>

    <div class="profiel-plaatje">
        <div class="titel-border">
            <h1 class="titel-p">Profiel bewerken</h1>
        </div>
    </div>

    <div class="profiel">
    <?php
    include("Back-end/Profiel/profile-info.php");

    if (isset($_GET['change'])) {
        if($_GET['change'] == "foutwijzig") {
            echo "Uw gegevens zijn niet gewijzigd.";
        }
    }

    if (isset($_GET['error'])) {
        if ($_GET['error'] == 'verkeerd') {
            echo '<tr><th colspan=2><p>Het ingevulde huidige wachtwoord is fout.</p></th></tr>';
        } elseif ($_GET['error'] == 'nietgelijk') {
            echo '<tr><th colspan=2><p>De ingevulde nieuwe wachtwoorden zijn niet gelijk.</p></th></tr>';
        }
    }

    ?>
    </div>
    </body>

    <?php include("Includes/footer.php"); ?>
</html>
