<?php
include "../../../config/db_config.php";

$requestStatus=$_POST['requestStatus'];
$userID=$_POST['userID'];

$select="SELECT * FROM requests r INNER JOIN elder e ON r.elder_id=e.elder_id
RIGHT JOIN care_giver  cg ON e.elder_id=cg.elder_id WHERE r.request_status='$requestStatus'
AND cg.staff_id='$userID'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
$response['status']=1;
$response['details']=array();
while($row=mysqli_fetch_array($query)){
    $index['requestID']=$row['request_id'];
    $index['name']=$row['firstname']." ".$row['lastname'];
    $index['gender']=$row['gender'];
    $index['requestDate']=$row['request_date'];
    $index['requestStatus']=$row['request_status'];
    
    array_push($response['details'],$index);
}
}else{
    $response['status']=0;
    $response['message']="No record found";
}
print json_encode($response);
?>