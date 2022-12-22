<?php
include'../config/db_config.php';

if($_SERVER['REQUEST_METHOD']==="POST"){
    $select=$_POST['select'];
    $username=$_POST['username'];
    $password=$_POST['password'];

    if($select=="Family member"){

        $update="UPDATE family_member SET password='$password' WHERE username='$username'";
    }else if($select=="Donor"){
        $update="UPDATE donors SET password='$password' WHERE username='$username'";
    }else if($select=="Supplier"){
        $update="UPDATE supplier SET password='$password' WHERE username='$username'";
    }else{
        $update="UPDATE staff SET password='$password' WHERE username='$username'";
    }

    if(mysqli_query($con,$update)){
        $response['status']=1;
        $response['message']="Password reset successfully";
    }else{
        $response['status']=0;
        $response['message']="Failed to reset password";
    }

    print json_encode($response);
}
?>