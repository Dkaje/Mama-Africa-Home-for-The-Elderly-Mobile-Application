<?php
include "../../../config/db_config.php";

$appointmentID=$_POST['appointmentID'];
$results=$_POST['results'];

$update="UPDATE appointment SET appointment_status='Test done'WHERE appointment_id='$appointmentID'";
if(mysqli_query($con,$update)){

    $update="UPDATE lab_test SET test_result='$results', test_status='Test done' WHERE appointment_id='$appointmentID'";
    mysqli_query($con,$update);
     $response['status']=1;
    $response['message']='Subitted successfully';
}else{
$response['status']=0;
$response['message']="Failed to update";
}
print json_encode($response);
?>