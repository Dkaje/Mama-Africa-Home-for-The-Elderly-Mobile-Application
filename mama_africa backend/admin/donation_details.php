<?php
include '../config/db_config.php';

$donationID=$_GET['get'];
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
    <?php


if(!empty($_SESSION['success'])){
    echo '<script>
    swal("Approved");
</script>';

   $_SESSION['success']='';
}
    ?>
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

       ?>
            <!-- Navbar End -->


            <!-- Table Start -->
            <div class="container-fluid pt-4 px-4">
                <div class="row g-4">
                    
                 
                    <div class="col-sm-12 col-xl-12 ">
                    
                             
                        <div class="card rounded h-100 p-4 ">
                            <h6 class="mb-4 text-dark">Items donated</h6>
                          
                            <table class="table table table-bordered  " id="printTable" class="dataTables_filter">
                                <thead>
                                    <tr>
                                        <th scope="col">Details</th>
                                        <th scope="col">ID</th>
                                        <th scope="col"> Name</th>
                                        <th scope="col">Phone number</th>
                                        <th scope="col">Donation date</th>
                                        <th scope="col">Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    $select ="SELECT * FROM donors d INNER JOIN donations ds ON d.donor_id=ds.donor_id
                                     WHERE  ds.donation_status='Delivered' OR ds.donation_id='$donationID'";
                                    $query=mysqli_query($con,$select);
                                    while($row=mysqli_fetch_array($query)){
                                        ?>
                                          <tr>
                                        <td>
                                            <a href="donation_details.php?get=<?php print $row['donation_id']?>" class="btn btn-info btn-sm">
                                        View
                                        </a>
                                     
                                    </td>
                                        <td scope="row"><?php print $row['donation_id']?></td>
                                        <td><?php print $row['first_name']." ".$row['last_name']?></td>
                                        <td><?php print $row['phone_no']?></td>
                                        <td><?php print $row['donation_date']?></td>
                                        <td><?php print $row['donation_status']?></td>
                                    </tr>
                                        <?php
                                    }
                                    ?>
                                  
                                 
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-sm-12 col-xl-5 ">
                    
                             
                    <div class="card rounded h-100 p-4  table-responsive">
                        <h6 class="text-dark ">Items</h6>

                        <table class="table">
                            <th>Item name</th>
                            <th>Quantity</th>
                            <?php
                            $select="SELECT * FROM items i INNER JOIN donated_items di ON i.item_id=di.item_id
                             WHERE di.donation_id='$donationID'";
                             $query=mysqli_query($con,$select);
                             while($row=mysqli_fetch_array($query)){
                                ?>
                                <tr>
                                    <td><?php print $row['item_name']?></td>
                                    <td><?php print $row['quantity']?></td>
                                </tr>
                                <?php
                             }
                            ?>
                            <tr>

                            </tr>
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
      title: 'Clients pending approval',
      filename: 'Clients pending approval',
     
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