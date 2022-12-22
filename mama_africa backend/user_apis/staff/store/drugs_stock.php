<?php
include '../../../config/db_config.php';


$select="SELECT * FROM  drugs ";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();
    while($row=mysqli_fetch_array($query)){

        $index['drugID']=$row['drug_id'];
        $index['drugName']=$row['drug_name'];
        $index['stock']=$row['stock'];
        array_push($response['details'],$index);
    }
}else{
    $response['status']=0;
    $response['mesage']='No record found';
}
print json_encode($response);
?>