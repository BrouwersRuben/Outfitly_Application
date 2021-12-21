let timestampLabelArray = [];

// temperatureArray.forEach((key, value) => {
//     timestamps.append(key);
//     temperatures.append(value);
// });

let timestamps = Object.keys(temperatureMap);
let temperatures = Object.values(temperatureMap);

let timestampsAlerts = Object.keys(weatherAlertMap);
let descriptions = Object.values(weatherAlertMap);

for (let i = 0; i < 26; i += 2) {
    let date = new Date(timestamps[i] * 1000);
    let hours = date.getHours();
    let minutes = '0' + date.getMinutes();

    timestampLabelArray.push(hours + ':' + minutes.substr(-2));
}

const ctx = document.getElementById('myChart').getContext('2d');

let gradient = ctx.createLinearGradient(0, 0, 0, 400);
gradient.addColorStop(0, 'rgba(58, 123, 213, 1');
gradient.addColorStop(1, 'rgba(0, 210, 255, 0.3');

let minRange = Math.min(...temperatures) - 2;

const myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: timestamps,
        datasets: [{
            data: temperatures,
            borderColor: '#3e95cd',
            backgroundColor: gradient,
            borderWidth: 1.5,
            fill: {value: minRange}
        }]
    },
    options: {
        plugins: {
            legend: {
                display: false
            }
        },
        responsive: true,
        maintainAspectRatio: false,
        elements: {
            point: {
                radius: 0
            }
        }
    }
});

if (weatherAlertMap.length !== 0) {
    document.getElementById('warning').innerText = 'Weather warnings for today.';
    document.getElementById('alerts').className = 'd-flex flex-column justify-content-center alert alert-warning';

    for (let i = 0; i < weatherAlertMap.length; i++) {
        let type1 = document.createElement('span');
        let type2 = document.createElement('span');
        let text1 = document.createTextNode('\n[' + unixTimeConverter(timestampsAlerts[i]) + ']');
        let text2 = document.createTextNode(descriptions[i]);
        type1.appendChild(text1);
        type2.appendChild(text2);
        document.getElementById('weatherAlerts').appendChild(type1);
        document.getElementById('weatherAlerts').appendChild(type2);
    }
}

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