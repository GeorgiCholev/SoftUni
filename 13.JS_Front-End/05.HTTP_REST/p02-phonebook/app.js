function attachEvents() {
    const BASE_URL = "http://localhost:3030/jsonstore/phonebook";

    let phonebook = document.getElementById("phonebook");

    let buttonLoad = document.getElementById("btnLoad");
    buttonLoad.addEventListener("click", loadJsonData);

    let buttonCreate = document.getElementById("btnCreate");
    buttonCreate.addEventListener("click", postEntry);

    function loadJsonData() {
        phonebook.innerHTML = "";
        fetch(BASE_URL)
            .then(resp => resp.json())
            .then(j => {
                Object.values(j).forEach(obj => {
                    let {person, phone, _id} = obj;
                    createPhonebookEntry(person, phone, _id);
                });
            })
            .catch(err => console.log(err));

    }

    function postEntry() {
        let entry = {};
        entry['person'] = document.getElementById("person").value;
        entry['phone'] = document.getElementById("phone").value;

        let httpContent = {
            method: "POST",
            body: JSON.stringify(entry)
        };

        fetch(BASE_URL, httpContent)
            .then(resp => resp.json())
            .then(() => loadJsonData())
            .catch(err => console.log(err));
    }

    function deleteEntry(e) {
        const id = e.target.id;
        fetch(BASE_URL + "/" + id, {method: "DELETE"})
            .then(resp => resp.json())
            .then(loadJsonData)
            .catch(err => console.log(err));
    }

    function createPhonebookEntry(person, phone, entryId) {
        let phonebookEntry = document.createElement("li");
        phonebookEntry.textContent = person + ": " + phone;

        let deleteButton = document.createElement("button");
        deleteButton.textContent = "Delete";
        deleteButton.id = entryId;
        deleteButton.addEventListener("click", e => deleteEntry(e));

        phonebookEntry.appendChild(deleteButton);
        phonebook.appendChild(phonebookEntry);
    }
}

attachEvents();