<?php
include '../config/db_config.php';

$firstname="";
$lastname="";
$username="";
$email="";
$phoneNo="";
$email="";
$userlevel="";
$password="";
$swal="";

if(isset($_POST['btnAdd'])){
    $firstname=$_POST['firstname'];
    $lastname=$_POST['lastname'];
    $username=$_POST['username'];
    $phoneNo=$_POST['phoneNo'];
    $email=$_POST['email'];
    $password=$_POST['password'];
    $userlevel=$_POST['userlevel'];


    if(empty($firstname)|| empty($lastname)|| empty($username)||
     empty($phoneNo)|| empty($email)||  empty($password)||  empty($userlevel)){
        $swal="error";
        $fb="Please enter all the details";

        }elseif(!preg_match ('/^([a-zA-Z]+)$/',$firstname)){
	$swal='error';
		$fb='Invalid first name. Should contain letters only';
}elseif(!preg_match ('/^([a-zA-Z]+)$/',$lastname)){
	$swal='error';
		$fb=' Invalid last name. Should contain letters only';
}elseif(!is_numeric($phoneNo)){
$swal='error';
		$fb='Phone number should contain digits only ';


}elseif(strlen($phoneNo)<>10){
$swal='error';
		$fb='Phone number should have 10 digit numbers';

}elseif(!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    $swal='error';
           $fb='Invalid email';
   
   
       }elseif(!preg_match ('/^([a-zA-Z]+)$/',$username)){
		$swal='error';
		$fb='invalid username. Should contain letters only';
}elseif(strlen($password)<4) {

	$swal='error';
		$fb='Password should have more than 4 characters';


}else{
        // check if user name exist
        $select="SELECT * FROM staff WHERE username='$username'";
        $query=mysqli_query($con,$select);
        if($check=mysqli_num_rows($query)>0){
            $swal="error";
            $fb="Username is in use";
        }else{

            // check if phone no exist
            $select="SELECT * FROM staff WHERE phone_no='$phoneNo'";
            $query=mysqli_query($con,$select);
            if($check=mysqli_num_rows($query)>0){
                $swal="error";
                $fb="Phone number is in use";
            }else{
                // check if email exist
                $select="SELECT * FROM staff WHERE email='$email'";
                $query=mysqli_query($con,$select);
                if($check=mysqli_num_rows($query)>0){
                    $swal="error";
                    $fb="Email is in use";
                }else{
                    $insert="INSERT INTO staff(f_name,l_name,username,phone_no,email,password,userlevel,role) VALUES 
                    ('$firstname','$lastname','$username','$phoneNo','$email','$password','$userlevel','Doctor')";
                    if(mysqli_query($con,$insert)){
                        $swal='success';
                        $fb='Added successfully';
                        $firstname="";
                        $lastname="";
                        $username="";
                        $phoneno="";
                        $email="";
                        $password="";
                        $userlevel="";
                    }else{
                        $swal='error';
                        $fb='Failed to add please try again';
                    }
                }
            }
        }
    }
}

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title><?php echo $siteName?></title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

   <?php
      include "../config/links.php";
   ?>

   <script src="js/custom-sweetalert.js"></script>
   <style>
.dataTables_filter input {
   box-shadow: 0 0 0px , 0 35px 1px #fff inset;
}
label{
    color:white;
    margin: 2px;
}
#input{
    color: white;
}
   </style>
</head>

<body class="btn-success">
    <div class="container-fluid position-relative d-flex p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-dark position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 33rem; height: 33rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->



        <!-- Content Start -->
        <div class="container-fluid">
            <!-- Navbar Start -->
            <?php
      include '../config/adminbar.php';

      if($swal=='error'){
        echo '<script>
        swal("'.$fb.'","error");
    </script>';
    }
    if($swal=='success'){
        echo '<script>
        swal("'.$fb.'","success");
    </script>';
    }
       ?>
            <!-- Navbar End -->


            <!-- Table Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="row g-4">
                    
                 
                    <div class="col-sm-12 col-xl-4 offset-2">
                      
                        <div class=" rounded h-100 p-4 ">
                            <h2 class="mb-4 ">Add new Doctor</h2>
                            <form method="post" autocomplete="off"> 
                              
                              <hr>
                              <label>First name</label>
                              <input type="text"name="firstname"class="form-control"id="input" value="<?php print $firstname?>"/> 
                         
                              <label>Last name</label>
                              <input type="text"name="lastname"class="form-control"id="input" value="<?php print $lastname?>"/> 
                         
                              <label>Phone no</label>
                              <input type="text"name="phoneNo"class="form-control"id="input" value="<?php print $phoneNo?>"/> 
                              <label>User role</label>
                              
                              <select name="userlevel" class="form-select" id="floatingSelect input"
                                    aria-label="Floating label select example">
                                    <option><?php print $userlevel?></option>
                                <option>General doctor</option>
                                <option>Othopedic surgeon</option>
                                </select>
                              <label>Email address</label>
                              <input type="text"name="email"class="form-control"id="input" value="<?php print $email?>"/> 
                              <label>Username</label>
                              <input type="text"name="username"class="form-control"id="input" value="<?php print $username?>"/> 
                         
                         
                              <label>Password</label>
                              <input type="password"name="password"class="form-control"id="input" value="<?php print $password?>"/> 
                         
                            <br>
                              <input type="submit"name="btnAdd"class=" btn btn-primary" value="Submit"/> 
                         
                            
                            </form>
                            
                        </div>
                    </div>
                   
                 
                </div>
            </div>
            <!-- Table End -->


            <!-- Footer Start -->
           
            <!-- Footer End -->
        </div>
        <!-- Content End -->


 </div>

    <?php
 include "../config/js_links.php";
    ?>



<!--  -->
<script>
	$(document).ready(function() {
  $('#printTable').DataTable({
    dom: 'Bfrtip',
    input:[{
        backgroungColor:"#ffff",
    }],
    buttons: [
         {
      extend: 'pdf',
      title: 'Staff',
      filename: 'Staff',
     
      exportOptions: {
         columns: [0,1,2]
                  },
    }, {
      extend: 'excel',
      title: 'Staff',
      filename: 'Staff',
      exportOptions: {
         columns: [0,1,2]
                  },
    },   
],

  });

});</script>

<!-- <script>
    $('#printTable').DataTable({
      
});
</script> -->
</body>

</html>