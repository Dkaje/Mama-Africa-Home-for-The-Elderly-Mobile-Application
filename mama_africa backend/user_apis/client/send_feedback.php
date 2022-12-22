<?php

include '../../config/db_config.php';

//insert

if ($_SERVER['REQUEST_METHOD'] =='POST') {

    $feedback = $_POST['feedback'];
    $userID = $_POST['userID'];
    $staffID = $_POST['staffID'];

    $insert="INSERT INTO feedback(comment,member_id,staff_id)VALUES 
                  ('$feedback','$userID','$staffID')";
    if(mysqli_query($con,$insert)){
    $response["status"] = 1;
    $response["message"] = "Feedback sent";

        } else {
        $response["status"] = 0;
        $response["message"] = "Failed to send";


    }
    echo json_encode($response);
}




