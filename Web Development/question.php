<!DOCTYPE html>
<html lang="nl">
  <head>
    <?php include("Includes/style.php");?>
  </head>

  <body>
    <?php
      session_start();
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
      <?php include("Back-end/Forum/vraag.php");?>

        <?php
          if (isset($_SESSION["gebruikersnaam"])) {
            $id = $_GET['id'];
            echo "<form action=Back-end/Forum/nieuwantwoord.php?id=$id method=post>";
            echo "<textarea name=bericht placeholder='Schrijf hier je antwoord' style=height: 150px> </textarea>";
            echo "<input type=submit value=Verstuur>";
            echo "</form>";
          }
        ?>

        <?php include("Back-end/Forum/antwoorden.php");?>

      </div>

      <div class="rightcolumn">
        <div class="darkcard">
          <h2>Gegevens</h2>
          <?php include("Back-end/Forum/gegevensVraag.php") ?>
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
