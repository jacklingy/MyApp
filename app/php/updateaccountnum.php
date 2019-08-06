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


$account=$_GET["account"];
$accountnum=$_GET["accountnum"];
//echo $account.$name."\n";

     #            "update users set name ='testname' where account=1;"
#注意语法格式，不然不能运行成功
$sql = "UPDATE `test`.`users` SET `account_num`=\"".$accountnum."\" WHERE `account`=\"".$account."\";";
#$sql = "UPDATE `test`.`users` SET `name`=\"$name\" WHERE 'account'=\"$account\";";
if (mysqli_query($conn, $sql)) {
	#修改成功
    echo "1";
} else {
    echo "0";
}




?>