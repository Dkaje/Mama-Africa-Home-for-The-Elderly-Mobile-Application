<?php
include '../../../config/db_config.php';
$supplierID=$_POST['supplierID'];

$select="SELECT * FROM s_request WHERE supplier_id='$supplierID'AND request_status='Cart'";
$query=mysqli_query($con,$select);
$row=mysqli_fetch_array($query);
$requestID=$row['request_id'];

$update="UPDATE s_request SET request_status='Pending approval' WHERE request_id='$requestID'";
if(mysqli_query($con,$update)){
  $update="UPDATE stock_in SET stock_in_status='Pending approval'WHERE request_id='$requestID'";
  mysqli_query($con,$update);
  $response['status']=true;
  $response['message']="Request sent sucessfully";
}else{
    $response['status']=false;
    $response['message']="Failed to send rquest";
}
print json_encode($response);

?>