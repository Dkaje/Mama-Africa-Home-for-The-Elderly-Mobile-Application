<?php
include '../../../config/db_config.php';

if($_SERVER['REQUEST_METHOD']==='POST'){

    $appointmentID=$_POST['appointmentID'];

    $update="UPDATE appointment SET d_status='Collected' WHERE appointment_id='$appointmentID'";
    if(mysqli_query($con,$update)){
        
        $up="UPDATE medication SET med_status='Collected' WHERE appointment_id='$appointmentID'";
        mysqli_query($con,$up);
        $response['status']=1;
        $response['message']='Sunmitted successfully';
    }else{
        $response['status']=0;
        $response['message']='Failed';
    }

    print json_encode($response);
}
?>