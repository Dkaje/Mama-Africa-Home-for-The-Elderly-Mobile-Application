<?php
include '../../../config/db_config.php';

$requestStatus=$_POST['requestStatus'];
$select="SELECT * FROM s_request r INNER JOIN supplier s ON r.supplier_id=s.supplier_id
WHERE r.request_status='$requestStatus'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();
    while($row=mysqli_fetch_array($query)){
        $index['requestID']=$row['request_id'];
        $index['name']=$row['first_name']." ".$row['last_name'];
        $index['requestDate']=$row['request_date'];
        $index['requestStatus']=$row['request_status'];

        array_push($response['details'],$index);
    }

}else{
    $response['status']=0;
    $response['message']='No data found';
}
print json_encode($response);
?>