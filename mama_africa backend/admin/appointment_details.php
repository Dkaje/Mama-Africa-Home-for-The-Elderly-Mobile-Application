<?php
session_start();
include '../config/db_config.php';
if(empty($_SESSION['userlevel'])){
    header('location:index.php');
}
$appointmentID=$_GET['get'];
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
                    
                             
                        <div class="card rounded h-100 p-4  table-responsive">
                            <h6 class="text-dark ">Details</h6>
                         
                             
                            
                            <table class="table table-bordered" id="" class="dataTables_filter">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Family member</th>
                                        <th scope="col">Elder name</th>
                                        <th scope="col">DOB</th>
                                        <th scope="col">Gender</th>
                                        <th scope="col">Contact</th>
                                        <th scope="col">Care giver</th>
                                        <th scope="col">Appointment Date</th>
                                        <th scope="col">Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    $select ="SELECT * FROM family_member fm INNER JOIN elder e ON fm.member_id=e.member_id
                                    RIGHT JOIN care_giver cg ON e.elder_id=cg.elder_id RIGHT JOIN staff s ON cg.staff_id=s.staff_id
                                    RIGHT JOIN appointment a ON e.elder_id=a.elder_id WHERE  a.appointment_id='$appointmentID'";
                                    $query=mysqli_query($con,$select);
                                    $row=mysqli_fetch_array($query);
                                        ?>
                                          <tr>
                                         
                                          <td scope="row"><?php print $row['appointment_id']?></td>
                                        <td><?php print $row['first_name']." ".$row['last_name']?></td>
                                        <td><?php print $row['firstname']." ".$row['lastname']?></td>
                                        <td><?php print $row['dob']?></td>
                                        <td><?php print $row['gender']?></td>
                                        <td><?php print $row['phone_no']?></td>
                                        <td><?php print $row['f_name']." ".$row['l_name']?></td>
                                        <td><?php print $row['appointment_date']?></td>
                                        <td><?php print $row['appointment_status']?></td>
                                    
                                    </tr>
                                 
                                </tbody>
                            </table>
                            <h4 style="color:black"> Doctor</h3>
                                    <?php
                                    $sell="SELECT * FROM staff s INNER JOIN appointment a ON s.staff_id=a.staff_id
                                     WHERE  a.appointment_id=".$row['appointment_id'];
                                     $query3=mysqli_query($con,$sell);
                                     $row3=mysqli_fetch_array($query3)
                                    ?>
                                    <p>Dr. <?php print $row3["f_name"]." ".$row3["l_name"]?></p>
                               <?php
                               if($row['appointment_remarks']==""|| $row['appointment_remarks']=="NULL"){
                                
                               }else{
                                ?>
                                 <h4 style="color:black"> Report</h3>
                                <?php
                                print $row['appointment_remarks'];
                               }
                               ?>
                                 
                        </div>
                    </div>

                    
                    <div class="col-sm-12 col-xl-6 ">
                    
                             
                        <div class="card rounded h-100 p-4  table-responsive">
                            <h6 class="text-dark ">Test</h6>
                         
                             
                            
                            <table class="table table-bordered" id="printTable" class="dataTables_filter">
                                <thead>
                                    <tr>
                                        <th scope="col">Test</th>
                                        <th scope="col">Results</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    $select ="SELECT * FROM lab_test WHERE appointment_id='$appointmentID'";
                                    $query=mysqli_query($con,$select);
                                    if(mysqli_num_rows($query)>0){
                                        while($row=mysqli_fetch_array($query)){
                                        ?>
                                           <tr>
                                         
                                         <td><?php print $row['test_details']?></td>
                                         <td><?php print $row['test_result']?></td>
                                     
                                     </tr>
                                        <?php
                                        }
                                    }else{
                                        ?>
                                           <tr class="text-center">
                                         
                                         <td >No record found</td>
                                         <td ></td>
                                     
                                     </tr>
                                       
                                        <?php
                                    }
                                    ?>
                                    
                                  
                                 
                                </tbody>
                            </table>
                        </div>
                    </div>
                    
                    <div class="col-sm-12 col-xl-6 ">
                    
                             
                        <div class="card rounded h-100 p-4  table-responsive">
                            <h6 class="text-dark ">Drugs</h6>
                         
                             
                            
                            <table class="table table-bordered" id="printTable" class="dataTables_filter">
                                <thead>
                                    <tr>
                                        <th scope="col">Drugs</th>
                                        <th scope="col">Quantity</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    $select ="SELECT * FROM medication m INNER JOIN drugs d ON m.drug_id=d.drug_id
                                     WHERE m.appointment_id='$appointmentID'";
                                    $query=mysqli_query($con,$select);
                                    if(mysqli_num_rows($query)>0){
                                        while($row=mysqli_fetch_array($query)){
                                        ?>
                                           <tr>
                                         
                                         <td><?php print $row['drug_name']?></td>
                                         <td><?php print $row['quantity']?></td>
                                     
                                     </tr>
                                        <?php
                                        }
                                    }else{
                                        ?>
                                           <tr class="text-center">
                                        
                                         <td >No record found</td>
                                         <td ></td>
                                     
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
      title: 'Elders admitted',
      filename: 'Elders admitted',
     
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