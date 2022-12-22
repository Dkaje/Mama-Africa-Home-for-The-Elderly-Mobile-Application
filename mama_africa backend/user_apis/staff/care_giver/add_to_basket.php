<?php

include '../../../config/db_config.php';

if($_SERVER['REQUEST_METHOD']==="POST"){
    $elderID=$_POST['elderID'];
    $itemName=$_POST['itemName'];
    $quantity=$_POST['quantity'];
    $itemdetail=$_POST['details'];

    $select="SELECT * FROM requests WHERE elder_id='$elderID' AND request_status='Cart'";
    $query=mysqli_query($con,$select);
    if(mysqli_num_rows($query)>0){

        $row=mysqli_fetch_array($query);
        $requestID=$row['request_id'];
}else{
    $insert="INSERT INTO requests (elder_id)VALUES('$elderID')";
    mysqli_query($con,$insert);
    $requestID=mysqli_insert_id($con);
}

  function addToBasket($con,$itemName,$itemdetail,$requestID,$quantity){
    
    $select="SELECT * FROM items WHERE item_name='$itemName'";
    $query=mysqli_query($con,$select);
    $row=mysqli_fetch_array($query);
    $stock=$row['stock'];
    $itemID=$row['item_id'];

    if($quantity>$stock){
        $response['status']=0;
        $response['message']=$stock." ".$itemName." in stock";
    }else{
    $select="SELECT * FROM item_requested WHERE item_id='$itemID'AND request_id='$requestID'
    AND issue_status='Cart'";
    $query=mysqli_query($con,$select);
    if(mysqli_num_rows($query)>0){
        $response['status']=0;
        $response['message']=$itemName." already added";
    }else{
        $insert="INSERT INTO item_requested(request_id,item_id,quantity,request_details)
        VALUES('$requestID','$itemID','$quantity','$itemdetail')";
        if(mysqli_query($con,$insert)){

            $update="UPDATE items SET stock=stock-'$quantity' WHERE item_id='$itemID'";
            mysqli_query($con,$update);
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

 addToBasket($con,$itemName,$itemdetail,$requestID,$quantity);

}
?>