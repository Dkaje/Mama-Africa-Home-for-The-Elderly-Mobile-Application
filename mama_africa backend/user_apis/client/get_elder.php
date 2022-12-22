<?php

include '../../config/db_config.php';

$userID=$_POST['userID'];

$select="SELECT *FROM elder WHERE member_id='$userID'";
$query=mysqli_query($con,$select);
if(mysqli_num_rows($query)>0){
    $response['status']="1";
    $response['details']=array();
    while($row=mysqli_fetch_array($query)){

        $index['elderID']=$row['elder_id'];
        $index['name']=$row['firstname']." ".$row['lastname'];
        $index['gender']=$row['gender'];
        $index['dob']=$row['dob']."\nRelation ".$row['relation'];
        $index['dateAdded']=$row['date_added'];
        $index['elderStatus']=$row['elder_status'];

    array_push($response["details"],$index);
    }

}else{
    $response['status']="0";
    $response['message']="No record found";
}
print json_encode($response);

?>