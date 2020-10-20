<!DOCTYPE html>
<html>
  <head>
    <?php include("Includes/style.php");?>
  </head>

  <body>
    <?php
      session_start();
      include("Includes/navbar.php");
    ?>

    <div class="forum">
      <div class="topcard">
        <h1 style="float: left;">Webtechnologie</h1>
        <h1 style="float: right;">Informatica</h1>
      </div>

      <div class="leftcolumn">
        <a href="newQuestion.php" style="text-decoration: none;">
          <div class="lightcard" id="question">
            <h2>Stel een vraag</h2>
          </div>
        </a>

        <?php include("Back-end/Forum/vragen.php");?>

      </div>

      <div class="rightcolumn">
        <div class="darkcard">
          <h2>Gegevens</h2>
          <?php include("Back-end/Forum/gegevens.php") ?>
        </div>
        <div class="darkcard">
          <h2>Andere forums</h2>
          <p>Inleiding Programmeren</p>
          <p>Architectuur en Computerorganisatie</p>
        </div>
      </div>
    </div>

    <?php include("Includes/footer.php");?>
  </body>
</html>
