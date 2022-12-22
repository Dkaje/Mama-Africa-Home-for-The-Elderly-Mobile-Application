<?php
include '../../../config/db_config.php';

$staffID=$_POST['userID'];

$select="SELECT DISTINCT(f.member_id) FROM feedback f INNER JOIN family_member fm
 WHERE f.staff_id='$staffID' ORDER BY f.fb_id DESC";
 $query=mysqli_query($con,$select);
 if(mysqli_num_rows($query)>0){
            $response['details']=array();
    $response['status']=1;
    while($row=mysqli_fetch_array($query)){

            $sle="SELECT * FROM family_member WHERE member_id=".$row['member_id'];
        $query1=mysqli_query($con,$sle);
            while($row1=mysqli_fetch_array($query1)){      
            $index['memberID']=$row1['member_id'];
            $index['name']=$row1['first_name']." ".$row1['last_name'];
            array_push($response['details'],$index);
        }
    }

 }else{
    $response['status']=0;
    $response['message']="No mesaage found";
 }
 print json_encode($response);
?>