<?php
include '../../../config/db_config.php';

$requestID=$_POST['requestID'];

$select="SELECT * FROM item_requested ir INNER JOIN items i ON ir.item_id=i.item_id
  WHERE ir.request_id='$requestID'";
  $query=mysqli_query($con,$select);
  if(mysqli_num_rows($query)>0){
    $reponse['status']=1;
    $response['details']=array();

    while($row=mysqli_fetch_array($query)){
        $index['itemName']=$row['item_name'];
        $index['quantity']=$row['quantity'];
        
        array_push($response['details'],$index);
    }

  }else{
    $reponse['status']=0;
    $response['message']='No record found';
  }
  print json_encode($reponse);
?>