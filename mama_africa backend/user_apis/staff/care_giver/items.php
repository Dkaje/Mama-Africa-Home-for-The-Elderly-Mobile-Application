<?php

include '../../../config/db_config.php';

$select="SELECT * FROM items";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();
    while($row=mysqli_fetch_array($query)){
        $index['item_id']=$row['item_id'];
        $index['itemName']=$row['item_name'];
        $index['stack']=$row['stock'];
        
        array_push($response['details'],$index);
    }

}else{
    $response['status']=0;
    $response['message']='No record found';
}
print json_encode($response);
?>