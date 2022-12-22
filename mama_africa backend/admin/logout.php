<?php

session_start();
unset($_SESSION["userlevel"]);
header('location:index.php');
?>