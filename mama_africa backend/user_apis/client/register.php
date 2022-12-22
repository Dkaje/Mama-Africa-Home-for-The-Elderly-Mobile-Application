<?php
include '../../config/db_config.php';
if ($_SERVER['REQUEST_METHOD'] =='POST') {
 
    $firstname = $_POST['firstname'];
    $lastname = $_POST['lastname'];
    $username = $_POST['username'];
    $phoneNo = $_POST['phoneNo'];
    $email = $_POST['email'];
    $password = $_POST['password'];

    if (empty($firstname) || empty($lastname) || empty($username) || empty($email) || empty($phoneNo) || empty($password)) {
        $response["status"] = 0;
        $response["message"] = "Some details are missing";
        echo json_encode($response);
        mysqli_close($con);
    } else {
        $select = "SELECT username FROM family_member WHERE username='$username'";
        $query = mysqli_query($con, $select);
        if (mysqli_num_rows($query) > 0) {
            $response["status"] = 0;
            $response["message"] = "Username not available";
            echo json_encode($response);
        } else {
            $select = "SELECT phone_no FROM family_member WHERE phone_no='$phoneNo'";
            $query = mysqli_query($con, $select);
            if (mysqli_num_rows($query) > 0) {
                $response["status"] = 0;
                $response["message"] = "Phone number not available";
                echo json_encode($response);
            } else {
                $select = "SELECT email FROM family_member WHERE email='$email'";
                $query = mysqli_query($con, $select);
                if (mysqli_num_rows($query) > 0) {
                    $response["status"] = 0;
                    $response["message"] = "Email not available";
                    echo json_encode($response);
                } else {
                    $insert = "INSERT INTO family_member(first_name, last_name, username, phone_no, email,password)
                VALUES ('$firstname','$lastname','$username','$phoneNo','$email','$password')";
                    if (mysqli_query($con, $insert)) {
                        $response["status"] = 1;
                        $response["message"] = "Registered successfully.";
                        echo json_encode($response);
                    } else {
                        $response["status"] = 0;
                        $response["message"] = "Failed to register";
                        echo json_encode($response);
                    }

                }
            }
        }
    }
}




