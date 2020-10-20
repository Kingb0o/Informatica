<!DOCTYPE html>
<html lang="nl">
  <head>
    <?php include("Includes/sessie.php");?>
    <?php include("Includes/style.php");?>
  </head>

  <body>
  <?php
      include("Includes/sessie.php");
      include("Includes/navbar.php");
      $dbconf = simplexml_load_file("mysql_config_collegedb.xml");
      include("Includes/database.php");
    ?>

    <div class="forum">
      <div class="topcard" id="left">
        <h1>Webtechnologie</h1>
      </div>
      <div class="topcard" id="right">
        <h1>Informatica</h1>
      </div>

      <div class="leftcolumn">
        <form action="Back-end/Forum/nieuwevraag.php" method="post">
          <textarea name="onderwerp" placeholder="Onderwerp" style="height: 70px; resize: none"> </textarea>
          <textarea name="bericht" placeholder="Schrijf hier je vraag" style="height: 240px"> </textarea>
          <input type="submit" value="Verstuur">
        </form>
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
        <div class="darkcard">
          <h2><a href="forum.php"> Terug naar het forum </a></h2>
        </div>
      </div>
    </div>

    <?php include("Includes/footer.php");?>
  </body>
</html>
