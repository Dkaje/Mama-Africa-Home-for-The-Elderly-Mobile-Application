<?php
include '../../../config/db_config.php';

if($_SERVER["REQUEST_METHOD"]=="POST"){
    $donationID=$_POST['donationID'];
    
    $update="UPDATE donations SET donation_status='Approved' WHERE donation_id='$donationID'";
    if(mysqli_query($con,$update)){
        $response['status']=1;
        $response['message']='Approved successdfully';
    }else{
        $response['status']=0;
        $response['message']='Failed to approve items';
    }

    print json_encode($response);
}
?>