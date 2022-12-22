<?php
include '../../../config/db_config.php';

$donationStatus=$_POST['donationStatus'];

$select="SELECT * FROM donors d INNER JOIN donations ds ON d.donor_id=ds.donor_id
 WHERE donation_status='$donationStatus'";
 $query=mysqli_query($con,$select);
 if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();

    while($row=mysqli_fetch_array($query)){
        $index['donationID']=$row['donation_id'];
        $index['name']=$row['first_name']." ".$row['last_name'];
        $index['phoneNo']=$row['phone_no'];
        $index['donationDate']=$row['donation_date'];
        $index['donationStatus']=$row['donation_status'];
        $index['donationRemarks']=$row['remarks'];

        array_push($response['details'],$index);
    }
 }else{
    $response['status']=0;
    $response['message']="No record found";
 }

 print json_encode($response);
?>