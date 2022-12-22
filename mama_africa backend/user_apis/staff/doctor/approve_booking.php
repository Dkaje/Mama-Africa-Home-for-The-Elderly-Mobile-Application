<?php
include "../../../config/db_config.php";

$appointmentID=$_POST['appointmentID'];

$update="UPDATE appointment SET appointment_status='Approved'WHERE appointment_id='$appointmentID'";
if(mysqli_query($con,$update)){

     $response['status']=1;
    $response['message']='Approved successfully';
}else{
$response['status']=0;
$response['message']="Failed to update";
}
print json_encode($response);
?>