<?php
include '../../../config/db_config.php';

$elderID=$_POST['elderID'];
$select="SELECT * FROM staff s INNER JOIN care_giver cg ON s.staff_id=cg.staff_id
 WHERE cg.elder_id='$elderID'";
$query=mysqli_query($con,$select);

if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();
    $row=mysqli_fetch_array($query);
        $index['staffID']=$row['staff_id'];
        $index['name']=$row['f_name']." ".$row['l_name'];
        $index['phoneNo']=$row['phone_no'];

        array_push($response['details'],$index);
    
}else{
    $response['status']=0;
    $response['message']='No record found';
}
print json_encode($response);
?>