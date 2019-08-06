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
else
echo "连接成功";

$account=$_GET["account"];
$name=$_GET["name"];
echo $account.$name."\n";

     #            "update users set name ='testname' where account=1;"
$sql = "UPDATE `test`.`users` SET `name`=\"".$name."\" WHERE `account`=\"".$account."\";";
#$sql = "UPDATE `test`.`users` SET `name`=\"$name\" WHERE 'account'=\"$account\";";
if (mysqli_query($conn, $sql)) {
	#修改成功
    echo "1";
} else {
    echo "0";
}




?>