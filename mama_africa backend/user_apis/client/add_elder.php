<?php
include '../../config/db_config.php';
if ($_SERVER['REQUEST_METHOD'] =='POST') {
 
    $userID = $_POST['userID'];
    $firstname = $_POST['firstname'];
    $lastname = $_POST['lastname'];
    $gender = $_POST['gender'];
    $relation = $_POST['relation'];
    $dob = $_POST['dob'];

    if (empty($firstname) || empty($lastname) || empty($gender) || empty($dob) ) {
        $response["status"] = 0;
        $response["message"] = "Some details are missing";
        echo json_encode($response);
        mysqli_close($con);
    } else {
                    $insert = "INSERT INTO elder(member_id,firstname, lastname, gender, dob,relation)
                VALUES ('$userID','$firstname','$lastname','$gender','$dob','$relation')";
                    if (mysqli_query($con, $insert)) {
                        $response["status"] = 1;
                        $response["message"] = "submited successfully.";
                        echo json_encode($response);
                    } else {
                        $response["status"] = 0;
                        $response["message"] = "Failed to submit";
                        echo json_encode($response);
                    }

                }
            }
 




