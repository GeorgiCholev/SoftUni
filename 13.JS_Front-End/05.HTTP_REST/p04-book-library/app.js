function attachEvents() {
    const BASE_URL = "http://localhost:3030/jsonstore/collections/books";

    let loadBtn = document.getElementById("loadBooks");
    loadBtn.addEventListener("click", getAllBooks);

    let submitFormElements = document.getElementById("form").children;
    submitFormElements[5].addEventListener("click", postNewBook);

    function getAllBooks() {
        let tableBody = document.querySelector("table > tbody");
        tableBody.innerHTML = "";

        fetch(BASE_URL)
            .then(resp => resp.json())
            .then(j => {
                Object.entries(j).forEach(e => addEntryToTable(e));
            })
            .catch(err => console.log(err));

        function addEntryToTable(e) {
            let id = e[0];
            let {author, title} = e[1];
            let newRow = createNewRow();
            tableBody.appendChild(newRow);

            function createNewRow() {
                let tr = document.createElement("tr");

                let titleTd = document.createElement("td");
                titleTd.textContent = title;
                tr.appendChild(titleTd);

                let authorTd = document.createElement("td");
                authorTd.textContent = author;
                tr.appendChild(authorTd);

                let functionalitiesTd = document.createElement("td");

                let editButton = document.createElement("button");
                editButton.addEventListener("click", (e) => editBook(e));
                editButton.id = id;
                editButton.textContent = "Edit";
                functionalitiesTd.appendChild(editButton);

                let deleteButton = document.createElement("button");
                deleteButton.addEventListener("click", (e) => deleteBook(e));
                deleteButton.id = id;
                deleteButton.textContent = "Delete";
                functionalitiesTd.appendChild(deleteButton);

                tr.appendChild(functionalitiesTd);
                return tr;
            }
        }

    }

    function postNewBook() {
        let titleInput = submitFormElements[2];
        let title = titleInput.value;

        let authorInput = submitFormElements[4];
        let author = authorInput.value;

        let postObj = {author, title};

        let httpContext = {
            method: "POST",
            body: JSON.stringify(postObj)
        };

        fetch(BASE_URL, httpContext)
            .then(resp => resp.json())
            .then(() => {
                fixForm();
                getAllBooks();
            })
            .catch(err => console.log(err));
    }

    function editBook(e) {
        submitFormElements[0].textContent = "Edit " + submitFormElements[0].textContent;

        let id = e.target.id;
        fetch(BASE_URL + "/" + id)
            .then(resp => resp.json())
            .then(j => {
                submitFormElements[2].value = j['title'];
                submitFormElements[4].value = j['author'];
            })
            .catch(err => console.log(err));

        let submitFormBtn = submitFormElements[5];
        submitFormBtn.textContent = "Save";

        let cleanBtn = submitFormBtn.cloneNode(true);
        submitFormBtn.parentNode.replaceChild(cleanBtn, submitFormBtn);
        cleanBtn.addEventListener("click", () => putEditedBook(id));

        function putEditedBook(id) {
            let title = submitFormElements[2].value;
            let author = submitFormElements[4].value;
            let putObj = {author, title};

            let httpContext = {
                method: "PUT",
                body: JSON.stringify(putObj)
            };

            fetch(BASE_URL + "/" + id, httpContext)
                .then(resp => resp.json())
                .catch(err => console.log(err));

            fixForm();
            getAllBooks();
        }
    }

    function deleteBook(e) {
        let bookId = e.target.id;

        fetch(BASE_URL + "/" + bookId, {method: "DELETE"})
            .then(resp => resp.json())
            .then(getAllBooks)
            .catch(err => console.log(err));
    }

    function fixForm() {
        submitFormElements[0].textContent = "FORM";
        let submitFormBtn = submitFormElements[5];
        submitFormBtn.textContent = "Submit";

        submitFormElements[2].value = "";
        submitFormElements[4].value = "";

        let cleanBtn = submitFormBtn.cloneNode(true);
        submitFormBtn.parentNode.replaceChild(cleanBtn, submitFormBtn);

        cleanBtn.addEventListener("click", postNewBook);
    }
}

attachEvents();