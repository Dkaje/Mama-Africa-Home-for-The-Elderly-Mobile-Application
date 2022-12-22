<?php
include '../../config/db_config.php';

if($_SERVER['REQUEST_METHOD']=="POST"){
    $userID=$_POST['userID'];

    $select="SELECT * FROM donations WHERE donor_id='$userID'AND donation_status='Cart'";
    $querry=mysqli_query($con,$select);
    $row=mysqli_fetch_array($querry);
    $donationID=$row['donation_id'];

    $update="UPDATE donations SET donation_status='Pending approval' WHERE donation_id='$donationID'";
    if(mysqli_query($con,$update)){
        $update="UPDATE donated_items SET status='Pending approval' WHERE donation_id='$donationID'";
        mysqli_query($con,$update);
        $response['status']=true;
        $response['message']="Donation submitted successfully";

    }else{
        $response['status']=false;
        $response['message']="Failed to submit.";
    }

    print json_encode($response);
}
?>