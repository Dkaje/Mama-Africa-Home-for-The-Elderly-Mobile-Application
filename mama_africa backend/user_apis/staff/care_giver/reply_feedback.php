<?php
include '../../../config/db_config.php';

if($_SERVER['REQUEST_METHOD']==='POST'){
    $userID=$_POST['userID'];
    $memberID=$_POST['memberID'];
    $reply=$_POST['reply'];

$insert="INSERT INTO feedback(member_id,staff_id,reply,comment)VALUES('$memberID','$userID','$reply','0')";
if(mysqli_query($con,$insert)){
    $response['status']=1;
    $response['message']="Sent";
}else{
    $response['status']=0;
    $response['message']="Failed to send";
}

print json_encode($response);
}
?>