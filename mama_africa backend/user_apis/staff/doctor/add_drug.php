<?php

if ($_SERVER['REQUEST_METHOD'] =='POST') {

    include '../../../config/db_config.php';

    $drugName=$_POST['drugName'];
    $quantity=$_POST['quantity'];
    $appointmentID=$_POST['appointmentID'];

    
    $select="SELECT * FROM drugs WHERE drug_name='$drugName'";
    $query=mysqli_query($con,$select);
    $row=mysqli_fetch_array($query);
    $drugID=$row['drug_id'];

    $select="SELECT * FROM medication WHERE appointment_id='$appointmentID'
    AND drug_id='$drugID'AND med_status='Cart'";
    $query=mysqli_query($con,$select);
    if(mysqli_num_rows($query)>0){
        $response['status']=0;
        $response['message']=$drugName. " already added";
    }else{
        $insert="INSERT INTO medication (appointment_id,drug_id,quantity)VALUES
        ('$appointmentID','$drugID','$quantity')";
        if(mysqli_query($con,$insert)){

            $update="UPDATE drugs SET stock=stock-'$quantity'WHERE drug_id='$drugID'";
            mysqli_query($con,$update);
            $response['status']=1;
            $response['message']="Added successfully";
        }else{
            $response['status']=0;
            $response['message']="Failed to add";
        }
    }
   
  print  json_encode($response);
    }
?>