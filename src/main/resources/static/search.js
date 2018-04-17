var search = function() {
    var searchButton = document.getElementById("searchbutton");

    // see tuleks siis ajaxiga
    var users = ["andri", "kermo", "paul"];


    searchButton.onclick = function() {
        var parent = this.parentNode;
        var next = this.nextSibling;
        this.classList.toggle("searching");
        if (this.classList.contains("searching")) {
            for (var i = 0; i < users.length; i++) {
                var newThing = document.createElement("a");
                newThing.classList.add("searchresult");
                newThing.innerHTML = users[i];
                parent.insertBefore(newThing, next);
            }
        } else {
            results = parent.getElementsByClassName("searchresult");
            while (results.length > 0) {
                results[0].parentNode.removeChild(results[0]);
            }
        }
    }
}
window.addEventListener('load', search)