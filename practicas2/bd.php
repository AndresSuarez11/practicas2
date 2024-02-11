<?php

$servidor="localhost"; // 127.0.0.1
$baseDeDatos="practicas2";
$usuario="root";
$contrasenia="";

try{
    $conexion= new PDO("mysql:host=$servidor;dbname=$baseDeDatos",$usuario,$contrasenia);
}catch(Exepcion $ex){
    echo $ex->getmessage();
}

?>