<?php
include '../../../config/db_config.php';
 $supplierID=$_POST['supplierID'];

 $select="SELECT * FROM s_request r INNER JOIN stock_in s ON r.request_id=s.request_id
 RIGHT JOIN drugs d ON s.drug_id=d.drug_id WHERE r.supplier_id='$supplierID' AND r.request_status='Cart';";
  $query=mysqli_query($con,$select);
  if(mysqli_num_rows($query)>0){
    
    $response['status']="1";
    $response['details']=array();

    while($row=mysqli_fetch_array($query)){
        $index['id']=$row['id'];
        $index['drugName']=$row['drug_name'];
        $index['quantity']=$row['quantity'];

        array_push($response['details'],$index);
    }
  }else{
    $response['status']="0";
    $response['message']="Select items to request";
  }
  print json_encode($response);
?>