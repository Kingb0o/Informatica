<!DOCTYPE html>
<html lang="nl">

  <head>
    <?php include("Includes/style.php");?>
  </head>

  <body>
    <?php include("Includes/navbar.php");?>

    <div class="forum">
      <div class="topcard" id="left">
        <h1>Nieuw Forum</h1>
      </div>

      <div class="leftcolumn">
        <form action="Back-end/nieuwForum.php" method="post">
          <textarea name="naam" placeholder="Vak" style="height: 70px; resize: none"> </textarea>
          <input class="vragen" type="submit" value="Maak">
        </form>
      </div>

      <div class="rightcolumn">
        <div class="darkcard">
          <h2>Gegevens</h2>
          <?php Include("Back-end/gegevensForums.php")?>
        </div>

      </div>
    </div>

    <?php include("Includes/footer.php");?>
  </body>
</html>
