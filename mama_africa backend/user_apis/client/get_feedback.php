<?php

include '../../config/db_config.php';


$userID=$_POST['userID'];
$staffID=$_POST['staffID'];

//creating a query
$select = "SELECT * FROM feedback f INNER JOIN family_member fm ON f.member_id=fm.member_id
WHERE f.member_id='$userID' AND f.staff_id='$staffID'";

  $query=mysqli_query($con,$select);
  if(mysqli_num_rows($query)>0){

      $results= array();
      $results['status'] = "1";
      $results['details'] = array();
      $results['message']="Feedback";
      while ($row=mysqli_fetch_array($query)){
          $temp = array();

          $temp['comment'] = $row['comment'];
          if( $row['reply']=="Pending"){
            $temp['reply'] ="0";
          }else{
            $temp['reply'] = $row['reply'];
          }
         
          array_push($results['details'], $temp);
      }


  }else{
    $results['status'] = "0";
      $results['message'] = "No feedback sent";

}
//displaying the result in json format
echo json_encode($results);



?>