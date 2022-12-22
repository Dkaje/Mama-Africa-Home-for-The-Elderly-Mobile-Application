<?php
 include '../config/db_config.php';


 if($_SERVER['REQUEST_METHOD']==="POST"){
    
 $requestID=$_POST['requestID'];
 $updateStatus=$_POST['updateStatus'];

 $update="UPDATE s_request SET request_status='$updateStatus'WHERE request_id='$requestID'";
 if(mysqli_query($con,$update)){

    $update="UPDATE stock_id SET stock_in_status='$updateStatus' WHERE request_id='$requestID'";
    mysqli_query($con,$update);

    $response['status']=1;
    $response['message']="Updated successfully";

      if($updateStatus=="Confirmed delivery"){
    $select="SELECT * FROM stock_in WHERE request_id='$requestID'";
    $query2=mysqli_query($con,$select);

    while($row=mysqli_fetch_array($query2)){
        $drugID=$row['drug_id'];
        $quantity=$row['quantity'];

        $upda="UPDATE drugs SET stock=stock+'$quantity' WHERE drug_id='$drugID'";
        mysqli_query($con,$upda);
    }
      }
 }else{
 $response['status']=0;
    $response['message']="Failed";
 }


 print json_encode($response);

 }
?>