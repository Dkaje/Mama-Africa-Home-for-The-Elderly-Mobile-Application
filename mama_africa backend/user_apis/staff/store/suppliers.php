<?php
include '../../../config/db_config.php';


$select="SELECT * FROM  supplier WHERE status='Approved' ";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();
    while($row=mysqli_fetch_array($query)){

        $index['supplierID']=$row['supplier_id'];
        $index['name']=$row['first_name']." ".$row['last_name'];
        $index['phoneNo']=$row['phone_no'];
        array_push($response['details'],$index);
    }
}else{
    $response['status']=0;
    $response['mesage']='No record found';
}
print json_encode($response);
?>