function attachEvents() {
    const URL = "http://localhost:3030/jsonstore/collections/students"

    let tableBody = document.querySelector("#results > tbody");
    tableBody.innerHTML = "";
    visualizeStudents();

    let submitBtn = document.getElementById("submit");
    submitBtn.addEventListener("click", postStudent);

    function visualizeStudents() {
        fetch(URL)
            .then(resp => resp.json())
            .then(j => Object.values(j).forEach(v => createRow(v)))
            .catch(err => console.log(err));

        function createRow({firstName, lastName, facultyNumber, grade}) {

            let row = document.createElement("tr");
            tableBody.appendChild(row);

            let firstNameData = document.createElement("td");
            firstNameData.textContent = firstName;
            row.appendChild(firstNameData);

            let lastNameData = document.createElement("td");
            lastNameData.textContent = lastName;
            row.appendChild(lastNameData);

            let facultyNumberData = document.createElement("td");
            facultyNumberData.textContent = facultyNumber;
            row.appendChild(facultyNumberData);

            let gradeData = document.createElement("td");
            gradeData.textContent = grade;
            row.appendChild(gradeData);
        }
    }

    function postStudent() {
        let [firstName, lastName, facultyNumber, grade] = Array.from(
            document.querySelector("#form .inputs").children
        ).map(child => child.value);

        let httpContext = {
            method: "POST",
            body: JSON.stringify({firstName, lastName, facultyNumber, grade})
        };

        fetch(URL, httpContext)
            .then(resp => resp.json())
            .then(attachEvents)
            .catch(err => console.log(err));
    }
}

attachEvents();