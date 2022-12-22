<?php
include '../../../config/db_config.php';
$elderID=$_POST['elderID'];

$select="SELECT * FROM requests WHERE elder_id='$elderID'AND request_status='Cart'";
$query=mysqli_query($con,$select);
$row=mysqli_fetch_array($query);
$requestID=$row['request_id'];

$update="UPDATE requests SET request_status='Pending approval' WHERE request_id='$requestID'";
if(mysqli_query($con,$update)){
  $update="UPDATE items_requested SET item_status='Submitted'WHERE request_id='$requestID'";
  mysqli_query($con,$update);
  $response['status']=true;
  $response['message']="Request sent sucessfully";
}else{
    $response['status']=false;
    $response['message']="Failed to send rquest";
}
print json_encode($response);

?>