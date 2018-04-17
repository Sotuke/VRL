var search = function() {
    var xhttp = new XMLHttpRequest();
    var searchButton = document.getElementById("searchbutton");

    searchButton.onclick = function() {
        this.classList.toggle("searchresults");
        if (this.classList.contains("searchresults")) {
            xhttp.open("GET", "/api/users", true);
            xhttp.send();
        } else {
            var parent = this.parentNode;
            var results = parent.getElementsByClassName("searchresult");
            while (results.length > 0) {
                results[0].parentNode.removeChild(results[0]);
            }
        }
    }
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            //console.log(this.responseText);
            var response = JSON.parse(this.responseText);
            var parent = searchButton.parentNode;
            var next = searchButton.nextSibling;
            for (var i = 0; i < response["username"].length; i++) {
                var newThing = document.createElement("a");
                newThing.classList.add("searchresult");
                newThing.innerHTML = response["username"][i];
                parent.insertBefore(newThing, next);
            }
        }
    }
}
window.addEventListener('load', search)