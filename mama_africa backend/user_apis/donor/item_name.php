<?php

include  '../../config/db_config.php';

$select="SELECT item_name FROM items";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();

    while($row=mysqli_fetch_array($query)){
        $index['itemName']=$row['item_name'];
         array_push($response['details'],$index);
    }

}else{
    $response['status']=0;
    $response['status']="No items found";
}
print json_encode($response);
?>