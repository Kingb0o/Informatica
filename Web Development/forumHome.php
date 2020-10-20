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
        <h1 style="float: center;">Forums</h1>
      </div>

      <div class="leftcolumn">
          <div class="lightcard">
            <div class="block">
                <h3> Vak </h3>
            </div>
            <div class="block">
                <h3> Studie </h3>
            </div>
            <div class="block">
                <h3> Bekijk </h3>
                <a href = "forum.php" class = "forumGa"> Ga naar forum </a>
            </div>
            <?php include("Back-end/Forum/forums.php");?>
          </div>

          <a href="newforum.php" style="text-decoration: none;">
          <div class="lightcard" id="question">
            <h2>Maak een forum</h2>
          </div>
        </a>
      </div>

      <div class="rightcolumn">
        <div class="darkcard">
          <h2>Gegevens</h2>
          <?php Include("Back-end/Forum/gegevensForums.php");?>
        </div>
      </div>
    </div>

    <?php include("Includes/footer.php");?>
  </body>
</html>



