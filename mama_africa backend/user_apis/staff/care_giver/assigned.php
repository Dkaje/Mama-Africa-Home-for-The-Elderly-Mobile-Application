<?php

include '../../../config/db_config.php';

$staffID=$_POST['staffID'];
$select="SELECT * FROM staff s INNER JOIN care_giver cg ON s.staff_id=cg.staff_id
RIGHT JOIN elder e ON e.elder_id=cg.elder_id RIGHT JOIN family_member fm ON e.member_id=fm.member_id 
WHERE cg.staff_id='$staffID'AND e.elder_status='Admitted'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();
    while($row=mysqli_fetch_array($query)){
        $index['elderID']=$row['elder_id'];
        $index['name']=$row['firstname']." ".$row['lastname'] ;
        $index['familyMember']=$row['first_name']." ".$row['last_name'] ;
        $index['phoneNo']=$row['phone_no'];
        $index['gender']=$row['gender'];
        $index['dob']=$row['dob'];
        $index['elderStatus']=$row['elder_status'];
        
        array_push($response['details'],$index);

    }
}else{
    $response['status']=0;
    $response['message']="No record";
}
print json_encode($response);
?>