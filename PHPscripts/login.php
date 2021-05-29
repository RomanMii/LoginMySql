<?php

	require 'init.php';

	$username = $_POST["username"];
	$password = $_POST["password"];

	if($connection){

		$sql = "select username from user where username='$username' and password ='$password'";
		$result = mysqli_query($connection,$sql);
		if(mysqli_num_rows($result)>0){

			$row = mysqli_fetch_assoc($result);
			$status = "ok";
			$result_code = 1;
			$username = $row['username'];
			echo json_encode(array('status'=>$status,'result_code'=>$result_code, 'username'=>$username));
		}else{

			$status = "ok";
			$result_code = 0;
			echo json_encode(array('status'=>$status,'result_code'=>$result_code));
		}
	}else{

		$status = "failed";
		echo json_encode(array('status' => $status, JSON_FORCE_OBJECT));
	}

	mysqli_close($connection);
?>
