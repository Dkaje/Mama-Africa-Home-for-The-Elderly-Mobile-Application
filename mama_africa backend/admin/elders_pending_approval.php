<?php
session_start();
include '../config/db_config.php';
if(empty($_SESSION['userlevel'])){
    header('location:index.php');
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
   <style>

.dataTables_filter input,label {
   box-shadow: 0 0 0px , 0 35px 1px #fff inset;
}
   </style>
</head>

<body class="bg-success">
    <div class="container-fluid position-relative d-flex p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-dark position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 23rem; height: 23rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->



        <!-- Content Start -->
        <div class="container-fluid">
            <!-- Navbar Start -->
            <?php
      include '../config/adminbar.php';

       ?>
            <!-- Navbar End -->


            <!-- Table Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="row g-4">
                    
                 
                    <div class="col-sm-12 col-xl-12 ">
                    
                             
                        <div class="card rounded h-100 p-4 ">
                            <h6 class="text-dark ">Elders pending approval</h6>
                         
                            <div class="col-sm-12 col-xl-6">
                      
                          
                            <div class="btn-group" role="group">
                            <a href="elders_admitted.php" type="button" class="btn btn-outline-primary">Admitted</a>
                                <a href="elders_approved.php" type="button" class="btn btn-outline-primary">Approved</a>
                                <a href="elders_pending_approval.php" type="button" class="btn btn-outline-primary">Pending approval</a>
                                <a href="elders_rejected.php" type="button" class="btn btn-outline-primary">Rejected</a>
                            </div>
                      
                    </div>
                     
                       
                            <table class="table table-bordered " id="printTable" class="dataTables_filter">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Family member</th>
                                        <th scope="col">Relationship</th>
                                        <th scope="col">Elder name</th>
                                        <th scope="col">DOB</th>
                                        <th scope="col">Gender</th>
                                        <th scope="col">Contact</th>
                                        <th scope="col">Date added</th>
                                        <th scope="col">Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    $select ="SELECT * FROM family_member fm INNER JOIN elder e ON fm.member_id=e.member_id
                                     WHERE  elder_status='Pending approval'";
                                    $query=mysqli_query($con,$select);
                                    while($row=mysqli_fetch_array($query)){
                                        ?>
                                          <tr>
                                          <td scope="row"><?php print $row['elder_id']?></td>
                                        <td><?php print $row['first_name']." ".$row['last_name']?></td>
                                        <td><?php print $row['relation']?></td>
                                        <td><?php print $row['firstname']." ".$row['lastname']?></td>
                                        <td><?php print $row['dob']?></td>
                                        <td><?php print $row['gender']?></td>
                                        <td><?php print $row['phone_no']?></td>
                                        <td><?php print $row['date_added']?></td>
                                        <td><?php print $row['elder_status']?></td>
                                    
                                    </tr>
                                        <?php
                                    }
                                    ?>
                                  
                                 
                                </tbody>
                            </table>
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
      title: 'Elders pending approval',
      filename: 'Elders pending approval',
     
      exportOptions: {
         columns: [0,1,2,3,4,5,6,7]
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