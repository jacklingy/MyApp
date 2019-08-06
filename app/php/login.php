<?php


//面向过程的mysql连接
$servername = "localhost";
$username = "root";
$password = "1234";
 
// 创建连接
$conn = mysqli_connect($servername, $username, $password);
 
// 检测连接
if (!$conn) {
    die("连接失败: " . mysqli_connect_error());
}
 




//检查登录
//接受传过来的参数

$account=$_GET["account"];
$password=$_GET["password"];

//echo $account;
//echo $password;

$sql = "SELECT *  FROM `mydb`.`users` WHERE `account` = \"".$account."\" AND `password`=\"".$password."\"";
//$sql = "SELECT * FROM `mydb`.`users` where `account=`tom`";
$result = mysqli_query($conn, $sql);
if (mysqli_num_rows($result) > 0){
	
	echo "1";
	

}else
echo "0";
//读取每一行
while($row = mysqli_fetch_assoc($result)){
	//echo "账号：".$row["account"]."密码：".$row["password"];
}

mysqli_close($conn);
?>