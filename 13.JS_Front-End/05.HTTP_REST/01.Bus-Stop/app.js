function getInfo() {
    let busId = document.getElementById("stopId").value;
    let stopNameTag = document.getElementById("stopName");
    let busesUl = document.getElementById("buses");
    busesUl.innerHTML = "";

    fetch("http://localhost:3030/jsonstore/bus/businfo/" + busId)
        .then(resp => resp.json())
        .then(j => {
            stopNameTag.textContent = j["name"];
            let entries = Object.entries(j["buses"]);
            entries.forEach(e => {
                let busInfo = document.createElement("li");
                busInfo.textContent = "Bus " + e[0] + " arrives in " + e[1] + " minutes";
                busesUl.appendChild(busInfo);
            })
        })
        .catch(() => stopNameTag.innerText = "Error");
}