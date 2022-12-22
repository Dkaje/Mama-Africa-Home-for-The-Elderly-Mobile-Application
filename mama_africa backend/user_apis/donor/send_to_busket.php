<?php
include'../../config/db_config.php';

if($_SERVER['REQUEST_METHOD']=='POST'){
    $itemName=$_POST['itemName'];
    $quantity=$_POST['quantity'];
    $userID=$_POST['userID'];

    $select="SELECT * FROM items WHERE item_name='$itemName'";
    $query=mysqli_query($con,$select);
    $row=mysqli_fetch_array($query);
    $itemID=$row['item_id'];

  $select="SELECT * FROM donations WHERE donor_id='$userID' AND donation_status='Cart'";
  $query=mysqli_query($con,$select);
  if(mysqli_num_rows($query)>0){
    $row=mysqli_fetch_array($query);
    $donationID=$row['donation_id'];

  }else{
    $insert="INSERT INTO donations(donor_id)VALUES('$userID')";
    $query=mysqli_query($con,$insert);
    $donationID=mysqli_insert_id($con);
    
  }

  function addItem($con,$donationID,$itemID,$itemName,$quantity){

    $select="SELECT * FROM donated_items WHERE item_id='$itemID'AND donation_id='$donationID'";
     $query=mysqli_query($con,$select);
     if(mysqli_num_rows($query)>0){
        $reseponse['status']=0;
        $reseponse['message']= $itemName." already added ";
        print json_encode($reseponse);
     }else{
        $insert="INSERT INTO donated_items(donation_id,item_id,quantity)
        VALUES('$donationID','$itemID','$quantity')";
        if(mysqli_query($con,$insert)){
            $reseponse['status']=1;
            $reseponse['message']= $itemName." added successfully";
            print json_encode($reseponse);
        }else{
            $reseponse['status']=0;
            $reseponse['message']= " Falled to add ";
            print json_encode($reseponse);
        }
     }

     print json_encode($reseponse);
  }
  addItem($con,$donationID,$itemID,$itemName,$quantity);

}

?>