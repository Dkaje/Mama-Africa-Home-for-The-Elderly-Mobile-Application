<?php
include '../../../config/db_config.php';

$appointmentID=$_POST['appointmentID'];

$select="SELECT * FROM staff s INNER JOIN appointment a ON s.staff_id=a.staff_id WHERE appointment_id='$appointmentID'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
while($row=mysqli_fetch_array($query)){
    $response['docDetails']="Dr. ".$row['f_name']." ".$row['l_name'].
    "\n".$row['userlevel']."\nPhone No: ".$row['phone_no'];
       
}
}else{
    $response['status']=0;
    $response['message']='No record found';
}
print json_encode($response);
?>