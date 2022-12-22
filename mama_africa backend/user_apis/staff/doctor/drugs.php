<?php
include '../../../config/db_config.php';

$select="SELECT * FROM drugs";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();
    while($row=mysqli_fetch_array($query)){
        $index['drugName']=$row['drug_name'];
        array_push($response['details'],$index);
    }
}else{
    $response['status']=0;
    $response['message']="No drugs found";
}
print json_encode($response);
?>