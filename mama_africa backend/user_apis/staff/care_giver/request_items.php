<?php
include '../../../config/db_config.php';

if($_SERVER['REQUEST_METHOD']=='POST'){

$elderID=$_POST['elderID'];
$itemID=$_POST['itemID'];
$quantity=$_POST['quantity'];

$select="SELECT *FROM requests WHERE elder_id='$elderID'AND request_status='Cart'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $row=mysqli_fetch_array($query);
    $requestID=$row['request_id'];
}else{
    $insert="INSERT INTO requests(elder_id)VALUES('$elderID')";
    $query=mysqli_query($con,$insert);
    $requestID=mysqli_insert_id($con);

}

function addItem($requestID,$itemID,$con,$quantity){

    $select="SELECT * FROM items WHERE item_id='$itemID' AND request_id='$requestID'";
    $query=mysqli_query($con,$select);
    if(mysqli_num_rows($query)>0){
        $response['status']=0;
        $response['message']="Item already added to request basket";
    }else{
        $insert="INSERT INTO items_requested(item_id,request_id,quantity)
        VALUES('$itemID','$requestID','$quantity')";
        if(mysqli_query($con,$select)){
            $response['status']=1;
            $response['message']="Submitted successfully";
        }else{
            $response['status']=0;
            $response['message']="Failed to submit";
        }
    }
print json_encode($response);
}

    addItem($requestID,$itemID,$con,$quantity);
}
?>