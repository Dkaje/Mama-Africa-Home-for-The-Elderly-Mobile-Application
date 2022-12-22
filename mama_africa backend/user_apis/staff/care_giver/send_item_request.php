<?php

include '../../../config/db_config.php';

if($_SERVER['REQUEST_METHOD']=='POST'){
    $requestID=$_POST['request_id'];

    $update="UPDATE requests SET request_status='Pending approval' WHERE request_id='$requestID'";
    if(mysqli_query($con,$update)){

        $update="UPDATE item_requested SET issue_status='Pending'WHERE request_id='$requestID'";
        mysqli_query($con,$update);
        
        $response['status']=1;
        $response['message']="Requests sent";

    }else{
        $response['status']=0;
    $response['message']="Failed to submit";
    }

    print json_encode($response);
}
?>