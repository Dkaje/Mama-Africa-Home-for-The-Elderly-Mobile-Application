<?php
include '../../../config/db_config.php';

$appointmentID=$_POST['appointmentID'];

$select="SELECT * FROM lab_test lt INNER JOIN appointment a ON lt.appointment_id=a.appointment_id
 WHERE lt.appointment_id='$appointmentID'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
while($row=mysqli_fetch_array($query)){
    $response['testDetails']="Test. ".$row['test_details'].
    "\nResult: ".$row['test_result'];
       
}
}else{
    $response['status']=0;
    $response['message']='No record found';
}
print json_encode($response);
?>