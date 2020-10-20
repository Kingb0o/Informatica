<!doctype html>
<html lang="en">

<head>
<?php include("Includes/style.php");?>
</head>

<body>
    <?php session_start();?>
    <?php include("Includes/navbar.php");?>

    <div class="banner">
        <div class="banner-transbox">
            <h1 class="titel">Welkom op HutSpot</h1>
            <h3 class="subtitel">De Spot voor UvA-studenten</h3>
        </div>
    </div>




    <div class="content">
        <div class="subjectdiv">
            <div class="subject">Informatica</div>
            <div class="subject">Beginners Vragen</div>
            <div class="subject">Cybersecurity</div>
        </div>

        <div class="subjectdiv">
            <div class="subject">Informatiekunde</div>
            <div class="subject">Beginners Vragen</div>
            <div class="subject">Communicatie</div>
        </div>

        <div class="subjectdiv">
            <div class="subject">Kunstmatige Intelligentie</div>
            <div class="subject">Beginners Vragen</div>
            <div class="subject">Deeplearning</div>
        </div>
    </div>

<?php include("Includes/footer.php");?>

</body>
</html>
