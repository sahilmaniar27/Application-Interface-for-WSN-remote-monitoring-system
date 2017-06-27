<?php
require "init.php";

$user_name = "lalit";
$location = "A501_quadrant_1";
$time = "10:00";

//$query_type = $_POST["query_type"];



$sql_query = "insert into user_info values('$user_name','$location','$time');";

if(mysqli_query($con,$sql_query))
{
	//echo "<h3>Database Insertion Success...</h3>";
	
}
else
{
	//echo "Database insertion failed".mysqli_error($con);
}

//$string = file_get_contents("http://www.angelfire.com/ri2/DMX/data.txt", "r");
//$fh = fopen($myFile, 'w') or die("Could not open: " . mysql_error());
//fwrite($fh, $string);
//fclose($fh);
/*
$sql = mysql_connect("localhost", "root", "password");
if (!$sql) {
    die("Could not connect: " . mysql_error());
}*/

//implementation using text file - fetch data from text file and put it into mysql database


$myFile = "C:/wamp64/www/NEA/data.txt";
$sql_query2 = "LOAD DATA LOCAL INFILE '$myFile'" . " INTO TABLE user_info FIELDS TERMINATED BY '|'";
$result = mysqli_query($con,$sql_query2);
if (!$result) {
    die("Could not load. " . mysqli_error($con));
}



//Socket implementation for receiving data from java file



 //}

?>