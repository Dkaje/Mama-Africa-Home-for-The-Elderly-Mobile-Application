<?php

include '../../../config/db_config.php';

$staffID=$_POST['staffID'];
// $testStatus=$_POST['testStatus'];

$select="SELECT * FROM care_giver cg INNER JOIN elder e ON cg.elder_id=e.elder_id
 RIGHT JOIN appointment a ON cg.id=a.id RIGHT JOIN family_member fm ON e.member_id=fm.member_id
 RIGHT JOIN staff s ON a.staff_id=s.staff_id RIGHT JOIN lab_test lt ON a.appointment_id=lt.appointment_id
  WHERE a.staff_id='$staffID'AND  a.appointment_status='Attended'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)){
    $response['status']=1;
    $response['details']=array();
    while($row=mysqli_fetch_array($query)){
        $index['appointmentID']=$row['appointment_id'];
        $index['familyMember']=$row['first_name']." ".$row['last_name'];
        $index['name']=$row['firstname']." ".$row['lastname'];
        $index['gender']=$row['gender'];
        $index['dob']=$row['dob'];
        $index['appointmentDate']=$row['appointment_date'];
        $index['appointmentDetails']=$row['appointment_details'];
        $index['careGiver']=$row['f_name']." ".$row['l_name'];
        $index['remarks']=$row['test_details'];
        $index['testStatus']=$row['test_status'];
        $index['testResults']=$row['test_result'];
        array_push($response['details'],$index);
    }

}else{
    $response['status']=0;
    $response['message']="No record found";
}
print json_encode($response);
?>