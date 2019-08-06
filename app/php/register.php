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


#$sql = "INSERT INTO `mydb`.`users` (`account`, `password`) VALUES (`".$account."`,`".$password."`);";

$sql = "INSERT INTO `mydb`.`users` (`account`, `password`) VALUES (\"".$account."\",\"".$password."\");";
if (mysqli_query($conn, $sql)) {
    echo "1";
} else {
    echo "0";
}
 
mysqli_close($conn);




?>