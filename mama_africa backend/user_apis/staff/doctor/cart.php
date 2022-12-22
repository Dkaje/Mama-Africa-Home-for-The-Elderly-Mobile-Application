<?php
include '../../../config/db_config.php';

$appointmentID=$_POST['appointmentID'];

$select="SELECT * FROM medication m INNER JOIN drugs d ON m.drug_id=d.drug_id
WHERE m.appointment_id='$appointmentID'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']=1;
    $response['details']=array();
    while($row=mysqli_fetch_array($query)){
        $index['medID']=$row['med_id'];
        $index['drugName']=$row['drug_name'];
        $index['quantity']=$row['quantity'];

        array_push($response['details'],$index);
    }
}else{
    $response['status']=0;
    $response['details']="No drugs added";
}
print json_encode($response);
?>