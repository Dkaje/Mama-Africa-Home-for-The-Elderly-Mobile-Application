<?php

include "../../../config/db_config.php";

$careGiverID=$_POST['careGiverID'];
$staffID=$_POST['staffID'];
$elderID=$_POST['elderID'];
$date=$_POST['date'];
$details=$_POST['details'];

$select="SELECT * FROM care_giver WHERE staff_id='$careGiverID'";
$query=mysqli_query($con,$select);
$row=mysqli_fetch_array($query);
$ID=$row['id'];

$insert="INSERT INTO appointment(id,staff_id,elder_id,appointment_date,appointment_details)
VALUES('$ID','$staffID','$elderID','$date','$details')";
if(mysqli_query($con,$insert)){
    $response['status']=1;
    $response['message']="Appointment booked successfully";
}else{
    $response['status']=0;
    $response['message']="Failed to book";

}
print json_encode($response);
?>