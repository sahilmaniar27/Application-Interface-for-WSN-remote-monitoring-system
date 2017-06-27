<?php
require "init.php";

$user_name = "lalit";
$time = "10:00:00";

//$sql_query = "select location from user_info where user_name like '$user_name' and time like '$time';";

$sql_query = "select * from user_info;";

$result = mysqli_query($con,$sql_query);

$response = array();

while($row = mysqli_fetch_array($result))
{
	array_push($response,array("name"=>$row[0],"location"=>$row[1],"time"=>$row[2]));	
}

echo json_encode(array("server_response"=>$response));
mysqli_close($con);
/* if(mysqli_num_rows($result)>0)
{
	$row = mysqli_fetch_assoc($result);
	$location_result = $row["location"];
	echo "<h3> The location of ". $user_name." at time ".$time." is ".$location_result."</h3>";
	
}
else
{
	echo "No info is available.....";
	echo "Fetching latest data from network......";
} */

?>