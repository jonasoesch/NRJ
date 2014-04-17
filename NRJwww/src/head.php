<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>NRJ</title>
    <meta name="description" content="A school project in the mobile web services course">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/nrj.css">
    <script src="js/lib/jquery-2.1.0.min.js"></script>
    <script src="js/lib/modernizr-2.6.2.min.js"></script>
    <script src="js/nrj.js"></script>
          <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {

    var json = 
[
    {
        "timestamp": 1326439500,
        "kW": 10
    }
    ,
    {
        "timestamp": 1329449500,
        "kW": 3
    }
    ,
    {
        "timestamp": 1332459500,
        "kW": 16
    }
    ,
    {
        "timestamp": 1336469500,
        "kW": 28
    }
    ,
    {
        "timestamp": 1326479500,
        "kW": 6
    }
    ,
    {
        "timestamp": 1326489500,
        "kW": 12
    }
    ,
    {
        "timestamp": 1326499500,
        "kW": 3
    }
    ,
    {
        "timestamp": 1326509500,
        "kW": 19
    }
    ,
    {
        "timestamp": 1326519500,
        "kW": 23
    }
    ,
    {
        "timestamp": 1326529500,
        "kW": 18
    }
    ,
    {
        "timestamp": 1326539500,
        "kW": 7
    }
    ,
    {
        "timestamp": 1326549500,
        "kW": 22
    }
    ,
    {
        "timestamp": 1326559500,
        "kW": 7
    }
]
        
    var arrayData = [
        ["timestamp", "kW"]
    ];

    var  day = 0;
    for(var i=0;i<json.length;i++) {
        var tempDate =  new Date(json[i].timestamp);
        var tempDateString = formatDate(tempDate, day);
        day = tempDate.getDay();
        console.log(tempDateString);
        arrayData[i+1] = [tempDateString, json[i].kW]
    }

        var data = google.visualization.arrayToDataTable(arrayData);
        console.log(data);

        var options = {
          title: 'Consumption'
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }


    function formatDate(date, existingDay) {
        var year = date.getFullYear();
        var month = date.getMonth()+1;
        var day = date.getDay();
        var hour = date.getHours();

        if (day===existingDay) {
            return ""+hour+":00";
        } else {
            return ""+day+"."+month+"."+year+" – "+hour+":00";
        }
    }
    </script>
</head>

<body>
    <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
    <header>
        <section class="warning">
            <input type="button" class="" value="Warning!" />
        </section>
    </header>
    <!-- Add your site or application content here -->