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
        
        html, body {
            height: 100%;
            width: 100%;
            margin: 0;
        }
        body, body > div {
            display: -webkit-box; display: -moz-box; display: box;
            -webkit-box-orient: vertical;-moz-box-orient: vertical;box-orient: vertical;
            -webkit-box-align: center;-moz-box-align: center;flex-align: center;
            -webkit-box-pack: center;-moz-box-pack: center;flex-pack: center;
        }
        
        body.weather-body {
          background-image: url('/weather/resources/images/backgrounds/home_bg_img.jpg');
          height: 100%;
          width: 100%;
          text-align:center;
        }

        .main-div {
            vertical-align: middle;
            text-align: center;
            width: 600px;
        } 

        .child-div {
            padding-top: 150px\9;
        }
        
        .weather-widget {
            font-size: 15px;
            display: none;
            margin:0 auto;
            border:solid 1px #C0F0B9;
            background:#D5FFC6;
            color:#48A41C; 
            font-family:Arial, Helvetica, sans-serif; font-size:14px; 
            font-weight:bold;
            text-align:left;
        }
        
        .weather-info-table {
            margin:0 auto;
        }
        
        .error-message {
            margin:0 auto;
            display: none;
            border:solid 1px #FBD3C6;
            background:#FDE4E1;
            color:#CB4721;
            font-family:Arial, Helvetica, sans-serif;
            font-size:14px;
            font-weight:bold;
        }
    </style>

</head>
<body class="weather-body">
    <div class="main-div">
        <div class="child-div">
            <div class="error-message" id="error-message-div"><div id="error-message"></div></div>
            <div class="weather-info-table">
                <table>
                    <tr>
                        <td><div id="weather-form" ></div></td>
                        <td><div id="weather-widget" class="weather-widget"></div></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</body>
</html>