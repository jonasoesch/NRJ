google.load("visualization", "1", {
    packages: ["corechart"]
});
//google.setOnLoadCallback(drawChart);
//drawChart;
function drawChart(dom) {

    var json = [
        {
            "timestamp": 1326439500,
            "kW": 10
        },
        {
            "timestamp": 1329449500,
            "kW": 3
        },
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

    var day = 0;
    for (var i = 0; i < json.length; i++) {
        var tempDate = new Date(json[i].timestamp);
        var tempDateString = formatDate(tempDate, day);
        day = tempDate.getDay();
        arrayData[i + 1] = [tempDateString, json[i].kW]
    }

    var data = google.visualization.arrayToDataTable(arrayData);

    var options = {
        title: 'Consumption'
    };
    var id = dom.find('.graph').attr('id');
    var chart = new google.visualization.LineChart(document.getElementById(id));
    chart.draw(data, options);
}


function formatDate(date, existingDay) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDay();
    var hour = date.getHours();

    if (day === existingDay) {
        return "" + hour + ":00";
    } else {
        return "" + day + "." + month + "." + year + " – " + hour + ":00";
    }
}