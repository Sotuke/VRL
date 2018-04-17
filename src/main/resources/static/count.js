function checkUpdates(url) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', "/api/test");
    xhr.onload = function() {
        var serverResponse = JSON.parse(xhr.responseText);
        document.getElementById("p1").innerHTML = serverResponse.number;
    };
    xhr.send();
}

setInterval(function() {checkUpdates('/updates')}, 10000);