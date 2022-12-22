<?php
include '../config/db_config.php';
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
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
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
                            <h6 class="mb-4 ">Staff</h6>
                            <table class="table table-bordered " id="printTable" class="dataTables_filter">
                                <thead>
                                    <tr>
                                        <th scope="col">Action</th>
                                        <th scope="col">ID</th>
                                        <th scope="col"> Name</th>
                                        <th scope="col">Username</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Contact</th>
                                        <th scope="col">User role</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    $select ="SELECT * FROM staff WHERE NOT userlevel='Admin'AND role='Staff'";
                                    $query=mysqli_query($con,$select);
                                    while($row=mysqli_fetch_array($query)){
                                        ?>
                                          <tr>
                                            <td><a href="#edit_staff.php?get=<?php print $row['staff_id']?>" class="btn btn-link btn-sm">
                                        Edit
                                        </a>
                                        <th scope="row"><?php print $row['staff_id']?></th>
                                        <td><?php print $row['f_name']." ".$row['l_name']?></td>
                                        <td><?php print $row['username']?></td>
                                        <td><?php print $row['phone_no']?></td>
                                        <td><?php print $row['email']?></td>
                                        <td><?php print $row['userlevel']?></td>
                                        
                                    </td>
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
      title: 'Staff',
      filename: 'Staff',
     
      exportOptions: {
         columns: [0,1,2,3,4,5,6]
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