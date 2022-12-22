<?php
session_start();
include "../config/db_config.php";
$username="";
$swal="";
$password="";

if(isset($_POST['btnLogin'])){
    $username=$_POST['username'];
    $password=$_POST['password'];


    if(empty($username)||empty($password)){
        $swal='error';
        echo $fb='Please enter username and password';

    }else{
        $select="SELECT * FROM staff WHERE username='$username' AND password='$password'
        AND userlevel='Admin'";
        $record=mysqli_query($con,$select);
        if($check=mysqli_num_rows($record)>0){
            $row=mysqli_fetch_array($record);
        $_SESSION['userlevel']=$row['userlevel'];
            header('location:dashboard.php');
            echo  'success.';
        }else{
            $swal='error';
            echo 	$fb=' Invalid login details';
        }

    }
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Login</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">


<!-- Libraries Stylesheet -->
<link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
<link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

<!-- Customized Bootstrap Stylesheet -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="css/style.css" rel="stylesheet">
<script src="js/custom-sweetalert.js"></script>
</head>

<body>
<?php
if($swal=='error'){
    echo '<script>
    swal("'.$fb.'");
</script>';
}
if($swal=='success'){
    echo '<script>
    swal("'.$fb.'");
</script>';
}
?>
    <div class="container-fluid position-relative d-flex p-0">
        <!-- Spinner Start -->
        <!-- <div id="spinner" class="show  position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 33rem; height: 33rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div> -->
        <!-- Spinner End -->


        <!-- Sign In Start -->
        <div class="container-fluid panel">
            <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
                <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-5"> 
                    <h3 class="text-light">
                            <?php print $siteName?>
                            </h3>
                    <div class="bg-success rounded p-4 p-sm-5 my-4 mx-3">
                        <div class="d-flex align-items-center justify-content-between mb-3">
                           
                            <h3>Login</h3>
                        </div>
                            <form method="post" autocomplete="off">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingInput" 
                            placeholder="Username" name="username"value="<?php print $username?>">
                            <label for="floatingInput">Username</label>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="password" class="form-control" id="floatingPassword"
                             placeholder="Password"name="password"value="<?php print $password?>">
                            <label for="floatingPassword">Password</label>
                        </div>
                        <div class="d-flex align-items-center justify-content-between mb-4">
                          
                          
                        </div>
                        <button type="submit" class="btn btn-primary py-3 w-100 mb-4" name="btnLogin">
                            Login
                        </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Sign In End -->
    </div>

    <!-- JavaScript Libraries -->
    <!-- <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script> -->
    <script src="lib/chart/chart.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/tempusdominus/js/moment.min.js"></script>
    <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
    <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>