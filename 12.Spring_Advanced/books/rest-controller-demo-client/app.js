
let loadBooksButton = document.getElementById("loadBooks");

loadBooksButton.addEventListener("click", loadBooks);

function loadBooks() {

    let booksContainer = document.getElementById("books-container");
    booksContainer.innerHTML = "";

    fetch("http://localhost:8080/api/books", {method: "GET"})
        .then(response => response.json())
        .then(json => json.forEach(book => {
            let row = document.createElement("tr");

            let titleCol = document.createElement("td");
            titleCol.textContent = book.title;

            let authorNameCol = document.createElement("td");
            authorNameCol.textContent = book.authorName;

            let isbnCol = document.createElement("td");
            isbnCol.textContent = book.isbn;

            let actionCol = document.createElement("td");

            let deleteButton = createDeleteButton(book);
            actionCol.appendChild(deleteButton);

            row.appendChild(titleCol);
            row.appendChild(authorNameCol);
            row.appendChild(isbnCol);
            row.appendChild(actionCol);

            booksContainer.appendChild(row);
        }))
}

function createDeleteButton(book) {
    let deleteButton = document.createElement("button");
    deleteButton.innerText = "Delete";
    deleteButton.dataset.id = book.id;
    deleteButton.addEventListener("click", deleteBook);
    return deleteButton;
}

function deleteBook(event) {
    let id = event.target.dataset.id;

    fetch(`http://localhost:8080/api/books/${id}`, {method: "DELETE"})
        .then(_ => loadBooks())
        .catch(e => console.log("error", e));
}