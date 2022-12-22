<?php

include '../../config/db_config.php';

//insert

if ($_SERVER['REQUEST_METHOD'] =='POST') {

    $username = $_POST['username'];
    $password = $_POST['password'];


    if ( empty($username) ||empty($password)) {
        $response["status"] = 0;
        $response["message"] = "Enter both username and password ";
        echo json_encode($response);
        mysqli_close($con);

    } else {

        // check if username already exists

        $select = "SELECT * FROM staff WHERE username='$username' AND password='$password'";
        $query = mysqli_query($con, $select);
        if (mysqli_num_rows($query) > 0) {
            while ($row=mysqli_fetch_array($query)){
               if($row['status']=='Active') {
                    $response['status'] = "1";
                    $response['details'] = array();
                    $response["message"] = "Login successful";
                    $index['userID']=$row['staff_id'];
                    $index['firstname']=$row['f_name'];
                    $index['lastname']=$row['l_name'];
                    $index['username']=$row['username'];
                    $index['phoneNo']=$row['phone_no'];
                    $index['email']=$row['email'];
                    $index['dateCreated']=$row['create_date'];
                    $index['user']=$row['userlevel'];
                    array_push($response['details'],$index);
                    echo json_encode($response);
                }else{
                    $response["message"] = "Account denied access.\n please contact the admin";
                }
            }
        } else {
            $response["status"] = 0;
            $response["message"] = "Wrong username or password";
            echo json_encode($response);
                }
            }
}




