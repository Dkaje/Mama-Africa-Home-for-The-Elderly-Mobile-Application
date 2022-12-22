<?php
include '../../../config/db_config.php';

$select="SELECT * FROM family_member fm INNER JOIN elder e ON fm.member_id=e.member_id
WHERE e.elder_status='Approved'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();
    while($row=mysqli_fetch_array($query)){

        $index['elderID']=$row['elder_id'];
        $index['name']=$row['firstname']." ".$row['lastname'];
        $index['familyMember']=$row['first_name']." ".$row['last_name'];
        $index['gender']=$row['gender'];
        $index['phoneNo']=$row['phone_no'];
        $index['dob']=$row['dob'];
        $index['dateAdded']=$row['create_date'];
        $index['elderStatus']=$row['elder_status'];
        array_push($response['details'],$index);
    }
}else{
    $response['status']=0;
    $response['message']='No record found';
}
print json_encode($response);
?>