<?php
include '../../../config/db_config.php';

$select="SELECT * FROM staff WHERE role='Doctor'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();
while($row=mysqli_fetch_array($query)){
    $index['staffID']=$row['staff_id'];
    $index['name']="Dr. ".$row['f_name']." ".$row['l_name'];
    $index['role']=$row['userlevel'];
    $index['phoneNo']=$row['phone_no'];

    array_push($response['details'],$index);
}
}else{
    $response['status']=0;
    $response['message']='No record found';
}
print json_encode($response);
?>