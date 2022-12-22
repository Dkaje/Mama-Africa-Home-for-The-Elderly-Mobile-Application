<?php
include '../../../config/db_config.php';
 $elderID=$_POST['elderID'];

 $select="SELECT * FROM item_requested ri INNER JOIN requests r ON ri.request_id=r.request_id RIGHT JOIN
 items i ON ri.item_id=i.item_id WHERE r.elder_id='$elderID'AND r.request_status='Cart'";
  $query=mysqli_query($con,$select);
  if(mysqli_num_rows($query)>0){
    
    $response['status']="1";
    $response['details']=array();

    while($row=mysqli_fetch_array($query)){
        $index['id']=$row['id'];
        $index['itemName']=$row['item_name'];
        $index['quantity']=$row['quantity'];
        $index['itemDetail']=$row['request_details'];

        array_push($response['details'],$index);
    }
  }else{
    $response['status']="0";
    $response['message']="Select items to request";
  }
  print json_encode($response);
?>