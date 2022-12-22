<?php
include "../../../config/db_config.php";

$appointmentID=$_POST['appointmentID'];
$details=$_POST['details'];

$update="UPDATE appointment SET appointment_status='Sent to lab'WHERE appointment_id='$appointmentID'";
if(mysqli_query($con,$update)){

    $insert="INSERT INTO lab_test(appointment_id,test_details)VALUES('$appointmentID','$details')";
    mysqli_query($con,$insert);
     $response['status']=1;
    $response['message']='Subitted successfully';
}else{
$response['status']=0;
$response['message']="Failed to update";
}
print json_encode($response);
?>