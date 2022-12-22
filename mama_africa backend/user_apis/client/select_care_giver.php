<?php
include '../../config/db_config.php';

$userID=$_POST['userID'];

$select="SELECT * FROM elder e INNER JOIN care_giver cg ON e.elder_id=cg.elder_id
RIGHT JOIN staff s ON cg.staff_id=s.staff_id WHERE e.member_id='$userID'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();
while($row=mysqli_fetch_array($query)){
    $index['staffID']=$row['staff_id'];
    $index['name']=$row['f_name']." ".$row['l_name'];
    $index['PhnoneNo']=$row['phone_no'];

    array_push($response['details'],$index);
}
}else{
    $response['status']=0;
    $response['message']="No record found";
}

print json_encode($response);
?>