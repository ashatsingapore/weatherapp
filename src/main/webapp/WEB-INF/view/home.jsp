<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Weather Service</title>
    <script type="text/javascript" src="resources/js/common/ext/ext-all.js"></script>
    <script type="text/javascript" src="resources/min/js/weatherapp-script.min.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/css/ext-all-gray.css" />
    <style type="text/css">
        body {
          background: url('/weather/resources/images/backgrounds/home_bg_img.jpg') no-repeat center top #b7b7b7;
          background-size: round auto; /* Height: auto is to keep aspect ratio */
          background-repeat: repeat;
        }
        .weather-info-table {
            position: fixed;
            padding-left:3px;
            display:block;
            position: center;
            padding-top:120px;
            padding-left:350px;
            padding-right:5px;
        }
        
        .weather-widget {
            font-size: 15px;
            width: 300px;
            display: none;
            position: center;
            padding: 180px 20px 20px 40px;
            padding:2px 4px;
            margin:0px;
            border:solid 1px #C0F0B9;
            background:#D5FFC6;
            color:#48A41C; 
            font-family:Arial, Helvetica, sans-serif; font-size:14px; 
            font-weight:bold;
            text-align:left;
        }
        

        .error-message {
            padding:2px 4px;
            margin:0px;
            display: none;
            border:solid 1px #FBD3C6;
            background:#FDE4E1;
            color:#CB4721;
            font-family:Arial, Helvetica, sans-serif;
            font-size:14px;
            font-weight:bold;
            text-align:center;
        }
    </style>

</head>
<body>
    <div class="error-message" id="error-message-div"><div id="error-message"></div></div>
    <div class="weather-info-table">
        <table>
            <tr>
                <td><div id="weather-form" ></div></td>
                <td><div id="weather-widget" class="weather-widget"></div></td>
            </tr>
        </table>
    </div>
</body>
</html>