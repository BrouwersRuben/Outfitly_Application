let city = document.getElementById('city');
let latitude = document.getElementById('latitude');
let longitude = document.getElementById('longitude');

// TODO: ENTITY WITH LATITUDE LONGITUDE IS NOT CREATED IDK WHY
// let api_url = 'https://api.openweathermap.org/data/2.5/onecall?lat='+latitude+'&lon='+longitude+'&exclude=minutely&units=metric&appid=ff81fe37ad2b546130b7cbcb331aa72c'
let api_url = 'https://api.openweathermap.org/data/2.5/onecall?lat=51.2198&lon=4.4027&exclude=minutely&units=metric&appid=ff81fe37ad2b546130b7cbcb331aa72c'

function unixTimeConverter(timestamp) {
    let time = new Date(timestamp * 1000);
    let year = time.getFullYear();
    let month = time.getMonth();
    let date = time.getDate();
    let hour = time.getHours();
    let min = time.getMinutes();
    let sec = time.getSeconds();
    return year + '-' + month + '-' + date + ' ' + hour + ':' + min + ':' + sec;
}

function drawChart(yAxis, xAxis) {
    const forecastChart = document.getElementById('myChart').getContext('2d');
    new Chart(forecastChart, {
        type: 'line',
        data: {
            labels: xAxis,
            datasets: [{
                data: yAxis,
                borderColor: '#3e95cd',
                fill: true,
            }]
        },
        options: {
            plugins: {
                legend: {
                    display: false
                }
            },
            responsive: true,
            maintainAspectRatio: false
        }
    });
}

function insertWeatherData() {
    fetch(api_url).then(function (response) {
        return response.json();
    }).then(function (obj) {

        if (obj.alerts[0].length != 0) {
            document.getElementById('warning').innerText = 'Weather warnings for today.';
            document.getElementById('alerts').className = 'd-flex flex-column justify-content-center alert alert-warning';

            for (let i = 0; i < obj.alerts.length; i++) {
                let type1 = document.createElement('span');
                let type2 = document.createElement('span');
                let text1 = document.createTextNode('\n[' + unixTimeConverter(obj.alerts[i].start) + ']');
                let text2 = document.createTextNode(obj.alerts[i].description);
                type1.appendChild(text1);
                type2.appendChild(text2);
                document.getElementById('weatherAlerts').appendChild(type1);
                document.getElementById('weatherAlerts').appendChild(type2);
            }
        }

        document.getElementById('weatherIcon').src = 'http://openweathermap.org/img/wn/' + obj.current.weather[0].icon + '@4x.png';
        document.getElementById('currentTemperature').innerText = obj.current.temp;
        document.getElementById('currentHumidity').innerText = obj.current.humidity;
        document.getElementById('currentWindSpeed').innerText = obj.current.wind_speed;
        document.getElementById('rainProbability').innerText = obj.daily[0].pop * 100;
        document.getElementById('maxTemperature').innerText = obj.daily[0].temp.max;
        document.getElementById('minTemperature').innerText = obj.daily[0].temp.min;
        document.getElementById('description').innerText = obj.daily[0].weather[0].description;

        const temperatureArray = [];
        const timestampArray = [];

        for (let i = 0; i < 20; i += 2) {
            temperatureArray.push(obj.hourly[i].temp)

            let date = new Date(obj.hourly[i].dt * 1000);
            let hours = date.getHours();
            let minutes = '0' + date.getMinutes();
            timestampArray.push(hours + ':' + minutes.substr(-2));
        }

        drawChart(temperatureArray, timestampArray);

    }).catch(function (error) {
        console.error('Couldn\'t get the weather data.');
        console.error(error);
    })
}

insertWeatherData();


