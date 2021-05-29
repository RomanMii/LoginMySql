<?php

	require 'init.php';	
	
	$username = $_POST['username'];
	$email = $_POST['email'];
	$password = $_POST['password'];
	
	
	if($connection){
		
		$sql = "select * from user where username='$username'"; 
		$result = mysqli_query($connection,$sql);
		
		if(mysqli_num_rows($result)>0){
			
			$status = "ok";
			$result_code = 0;
			echo json_encode(array('status'=>$status, 'result_code'=>$result_code));
		}else{
			
			$sql = "insert into user(username,email,password) values('$username','$email','$password')";
			if(mysqli_query($connection,$sql)){
				
				$status = "ok";
				$result_code = 1;
				echo json_encode(array('status'=>$status,'result_code'=>$result_code));
			}else{
				
				$status = "failed";
				echo json_encode(array('status'=>$status, JSON_FORCE_OBJECT));
			}
		}
	}else{
	
		$status = "failed";
		echo json_encode(array('status'=>$status, JSON_FORCE_OBJECT));
	}
	
	mysqli_close($connection);
?>