<?php

include '../../../config/db_config.php';

if($_SERVER['REQUEST_METHOD']==="POST"){
    $supplierID=$_POST['supplierID'];
    $drugName=$_POST['drugName'];
    $quantity=$_POST['quantity'];
    $select="SELECT * FROM s_request WHERE supplier_id='$supplierID' AND request_status='Cart'";
    $query=mysqli_query($con,$select);
    if(mysqli_num_rows($query)>0){

        $row=mysqli_fetch_array($query);
        $requestID=$row['request_id'];
}else{
    $insert="INSERT INTO s_request (supplier_id)VALUES('$supplierID')";
    mysqli_query($con,$insert);
    $requestID=mysqli_insert_id($con);
}

  function addToBasket($con,$drugName,$requestID,$quantity){
    
    $select="SELECT * FROM drugs WHERE drug_name='$drugName'";
    $query=mysqli_query($con,$select);
    $row=mysqli_fetch_array($query);
    $stock=$row['stock'];
    $drugID=$row['drug_id'];

    if($quantity>$stock){
        $response['status']=0;
        $response['message']=$stock." ".$drugName." in stock";
    }else{
    $select="SELECT * FROM stock_in WHERE drug_id='$drugID'AND request_id='$requestID'
    AND stock_in_status='Cart'";
    $query=mysqli_query($con,$select);
    if(mysqli_num_rows($query)>0){
        $response['status']=0;
        $response['message']=$drugName." already added";
    }else{
        $insert="INSERT INTO stock_in(request_id,drug_id,quantity)
        VALUES('$requestID','$drugID','$quantity')";
        if(mysqli_query($con,$insert)){
            $response['status']=1;
        $response['message']="Added successfully";
        }else{
            $response['status']=0;
        $response['message']="Failed to add";
        }
    }
    }

  print json_encode($response);

  }

 addToBasket($con,$drugName,$requestID,$quantity);

}
?>