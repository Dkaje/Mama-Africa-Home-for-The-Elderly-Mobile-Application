<?php

include '../../../config/db_config.php';

$elderID=$_POST['elderID'];
$staffID=$_POST['staffID'];

$insert="INSERT INTO care_giver(staff_id,elder_id)VALUES('$staffID','$elderID')";
if(mysqli_query($con,$insert)){

    $update="UPDATE elder SET elder_status='Admitted'WHERE elder_id='$elderID'";
    mysqli_query($con,$update);
    $response['status']=1;
    $response['message']='Elder addmitted';

}else{
    $response['status']=0;
    $response['message']="Failed to submit";
}
print json_encode($response);
?>