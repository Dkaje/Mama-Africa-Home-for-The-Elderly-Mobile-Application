<?php

include '../../config/db_config.php';

if ($_SERVER['REQUEST_METHOD'] =='POST') {

    $username = $_POST['username'];
    $password = $_POST['password'];


    if ( empty($username) ||empty($password)) {
        $response["status"] = 0;
        $response["message"] = "Enter both username and password ";
        echo json_encode($response);
        mysqli_close($con);

    } else {

        $select = "SELECT * FROM donors WHERE username='$username' AND password='$password'";
        $query = mysqli_query($con, $select);
        if (mysqli_num_rows($query) > 0) {
            while ($row=mysqli_fetch_array($query)){
                if($row['status']=='Pending approval'){
                    $response["status"] = 0;
                    $response["message"] = "Please wait for activation";
                    echo json_encode($response);
                }elseif ($row['status']=='Rejected'){
                    $response["user_status"] = 0;
                    $response["message"] = "Account denied access.\n".$row['remarks'];
                    echo json_encode($response);
                }elseif($row['status']=='Approved') {
                    $response['status'] = "1";
                    $response['details'] = array();
                    $response["message"] = "Login successful";
                    $index['userID']=$row['donor_id'];
                    $index['firstname']=$row['first_name'];
                    $index['lastname']=$row['last_name'];
                    $index['username']=$row['username'];
                    $index['phoneNo']=$row['phone_no'];
                    $index['email']=$row['email'];
                    $index['dateCreated']=$row['date_created'];
                    $index['user']="Donor";
                    array_push($response['details'],$index);
                    echo json_encode($response);
                }
            }
        } else {
            $response["status"] = 0;
            $response["message"] = "Wrong username or password";
            echo json_encode($response);
                }
            }
}




