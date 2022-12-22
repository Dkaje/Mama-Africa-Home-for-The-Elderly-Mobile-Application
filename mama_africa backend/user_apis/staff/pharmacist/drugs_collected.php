<?php

// use LDAP\Result;

include '../../../config/db_config.php';


$select="SELECT * FROM family_member fm INNER JOIN elder e ON fm.member_id=e.member_id
RIGHT JOIN care_giver cg ON e.elder_id=cg.elder_id
RIGHT JOIN appointment a ON e.elder_id=a.elder_id 
 RIGHT JOIN staff s ON a.staff_id=s.staff_id WHERE  a.d_status='Collected'";
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
        $index['dStatus']=$row['d_status'];
        $index['appointmentDetails']=$row['appointment_details'];
                  $appointmentID=$row['appointment_id'];
            $sql="SELECT * FROM lab_test WHERE appointment_id='$appointmentID'";
            $qly=mysqli_query($con,$sql);
            $rowR=mysqli_fetch_array($qly);
            $index['appointmentDetails']=$row['appointment_details'].
            "\nResult: ".$rowR['test_result'];

          array_push($response['details'],$index);
    }

}else{
    $response['status']=0;
    $response['message']="No record found";
}
print json_encode($response);
?>