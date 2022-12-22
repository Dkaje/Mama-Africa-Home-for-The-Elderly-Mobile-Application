<?php
include '../../../config/db_config.php';

if($_SERVER['REQUEST_METHOD']==="POST"){

    $drugName=$_POST['drugName'];
    $quantity=$_POST['quantity'];

    $update="UPDATE drugs SET stock=stock+'$quantity'WHERE drug_name='$drugName'";
    if(mysqli_query($con,$update)){
        $response['status']=1;
        $response['message']="Update successfully";
    }else{
        $response['status']=0;
        $response['message']="Failed";
    }

    print json_encode($response);
}
?>