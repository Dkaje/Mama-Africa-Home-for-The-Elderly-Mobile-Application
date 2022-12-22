<?php
include "../../../config/db_config.php";

$requestID=$_POST['requestID'];

$update="UPDATE requests SET request_status='Approved'WHERE request_id='$requestID'";
if(mysqli_query($con,$update)){

    $updet="UPDATE item_requested SET item_status='Approved'WHERE request_id='$requestID'";
    mysqli_query($con,$updet);

     $response['status']=1;
    $response['message']='Approved successfully';
}else{
$response['status']=0;
$response['message']="Failed to update ";
}
print json_encode($response);
?>