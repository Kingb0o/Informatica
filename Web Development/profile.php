<!DOCTYPE html>
<html>
    <head>
        <?php include("Includes/sessie.php");?>
        <?php include("Includes/style.php");?>
    </head>

    <body>
        <?php include("Includes/navbar.php");?>

        <div class="profiel-plaatje">
            <div class="titel-border">
                <h1 class="titel-p">Profielpagina</h1>
            </div>
        </div>

        <p class="feedback">
            <?php
            if (isset($_GET['change'])) {
                if($_GET['change'] == "gewijzigd") {
                    echo "Uw gegevens zijn aangepast.";
                }

                if($_GET['change'] == "ingelogd") {
                    echo "U bent al ingelogd.";
                }
            }
            ?>
        </p>

        <?php include("Back-end/Profiel/viewProfile.php");?>

        <?php include("Includes/footer.php");?>
    </body>
</html>
