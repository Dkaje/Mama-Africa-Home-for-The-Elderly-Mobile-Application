<?php
include '../../config/db_config.php';

$userID=$_POST['userID'];
$requestStatus=$_POST['requestStatus'];

$select="SELECT * FROM s_request WHERE supplier_id='$userID' AND request_status='$requestStatus'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();
    while($row=mysqli_fetch_array($query)){
        $index['requestID']=$row['request_id'];
        $index['requestDate']=$row['request_date'];
        $index['requestStatus']=$row['request_status'];

        array_push($response['details'],$index);
    }
}else{
    $response['status']=0;
    $response['message']='No record found';

}
print json_encode($response);
?>