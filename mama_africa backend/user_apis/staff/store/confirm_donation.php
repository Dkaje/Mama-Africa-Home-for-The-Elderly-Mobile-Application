<?php
include '../../../config/db_config.php';

if($_SERVER["REQUEST_METHOD"]=="POST"){
    $donationID=$_POST['donationID'];
    
    $update="UPDATE donations SET donation_status='Confirmed delivery' WHERE donation_id='$donationID'";
    if(mysqli_query($con,$update)){

        $select="SELECT * FROM donated_items WHERE donation_id='$donationID'";
        $query=mysqli_query($con,$select);
        while($row=mysqli_fetch_array($query)){
            $quantity=$row['quantity'];
            $itemID=$row['item_id'];

            $update2="UPDATE items SET stock=stock+'$quantity'WHERE item_id='$itemID'";
            mysqli_query($con,$update2);
        }
        $response['status']=1;
        $response['message']='Confirmed successdfully';
    }else{
        $response['status']=0;
        $response['message']='Failed to approve items';
    }

    print json_encode($response);
}
?>