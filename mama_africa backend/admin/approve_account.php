<?php
session_start();
include '../config/db_config.php';
if(empty($_SESSION['userlevel'])){
    header('location:index.php');
}

$swal="";

if(isset($_POST['btnApprove'])){
    $memberID=$_GET['get'];

    $update="UPDATE family_member SET status='Approved' WHERE member_id='$memberID'";
    if(mysqli_query($con,$update)){
        $_SESSION['success']="Appoved successfully";
        header("location:clientpending.php");
    }else{
        $swal='error';
        $fb='Failed to approve';
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

<body class="bg-success">
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
        swal("'.$fb.'");
    </script>';
    }
    if($swal=='success'){
        echo '<script>
        swal("'.$fb.'");
    </script>';
    }
       ?>
            <!-- Navbar End -->


            <!-- Table Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="row g-4">
                    
                 
                    <div class="col-sm-12 col-xl-4 offset-4">
                      
                        <div class="card rounded h-100 p-4 ">
                            <h6 class="mb-4 text-dark">Approve</h6>
                            <form method="post" autocomplete="off"> 
                             
                              <hr>
                            
                              <select name="select" class="form-select" id="floatingSelect input"
                                    aria-label="Floating label select example" required>
                                    <option></option>
                                <option>Approve</option>
                                
                                </select>
                            <br>
                              <input type="submit"name="btnApprove"class=" btn btn-info" value="Approve"/> 
                         
                            
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


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
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