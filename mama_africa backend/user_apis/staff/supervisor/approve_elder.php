<?php
include "../../../config/db_config.php";

$elderID=$_POST['elderID'];
// $staffID=$_POST['staffID'];

$update="UPDATE elder SET elder_status='Approved'WHERE elder_id='$elderID'";
if(mysqli_query($con,$update)){

    // $insert="INSERT INTO care_giver (elder_id,staff_id)WHERE('$elderID','$staffID')";
    // mysqli_query($con,$insert);
    
    $response['status']=1;
    $response['message']='Approved successfully';
}else{
$response['status']=0;
$response['message']="Failed to update";
}
print json_encode($response);
?>