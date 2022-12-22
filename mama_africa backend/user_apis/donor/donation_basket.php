<?php
include '../../config/db_config.php';

$userID=$_POST['userID'];

$select="SELECT * FROM donated_items di INNER JOIN items i ON di.item_id=i.item_id 
RIGHT JOIN donations d ON di.donation_id=d.donation_id WHERE d.donor_id='$userID'
AND d.donation_status='Cart'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();

    while($row=mysqli_fetch_array($query)){

        $index['itemID']=$row['item_id'];
        $index['itemName']=$row['item_name'];
        $index['quantity']=$row['quantity'];
        array_push($response['details'],$index);
    }
}else{
    $response['status']=0;
    $response['mesage']='No record found';
}
print json_encode($response);
?>