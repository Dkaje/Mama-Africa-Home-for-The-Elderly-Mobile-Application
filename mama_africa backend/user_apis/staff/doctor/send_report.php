<?php

include '../../../config/db_config.php';
$appointmentID=$_POST['appointmentID'];
$report=$_POST['report'];

$update="UPDATE appointment SET appointment_status='Attended',
 appointment_remarks='$report',d_status='Pending'WHERE appointment_id='$appointmentID'";
if(mysqli_query($con,$update)){

    $update="UPDATE medication SET med_status='Pending collection' WHERE appointment_id='$appointmentID'";
    mysqli_query($con,$update);
    $response['status']=true;
    $response['message']="Submitted successfully";
}else{
    $response['status']=false;
    $response['message']="Failed to submit";
}
print json_encode($response);
?>