<?php

include '../../config/db_config.php';

$userID=$_POST['userID'];

$select="SELECT * FROM  donations WHERE donor_id='$userID' AND NOT donation_status='Cart'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();
 while($row=mysqli_fetch_array($query)){
    $index['donationID']=$row['donation_id'];
    $index['donationStatus']=$row['donation_status'];
    $index['donationDate']=$row['donation_date'];
    $index['donationRemarks']=$row['donation_remarks'];
    
    array_push($response['details'],$index);
 }
}else{
    $response['status']=0;
    $response['message']="No donations found";
}
print json_encode($response);
?>