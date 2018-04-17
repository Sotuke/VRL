var xhr = new XMLHttpRequest(),
    IP_ADDRESS;

xhr.onreadystatechange = function() {
    if (xhr.readyState==4 && xhr.status==200) {
        IP_ADDRESS = JSON.parse(xhr.responseText).ip;
        console.log('IP ADDRESS: ' + IP_ADDRESS);
        // Log it or do something else so you'll know that the response has been received
    }
}

xhr.open('GET', 'http://jsonip.com/', true);
xhr.send();