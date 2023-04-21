function addItem() {
    let ulContainer = document.getElementById("items");

    let inputField = document.getElementById("newItemText");
    let newItemText = inputField.value;
    let newItem = document.createElement("li");
    newItem.textContent = newItemText;

    let deleteAnchor = document.createElement("a");
    deleteAnchor.href = "#";
    deleteAnchor.textContent = "[Delete]"
    deleteAnchor.addEventListener("click", (e) => {e.target.parentElement.remove()})

    newItem.appendChild(deleteAnchor);

    ulContainer.appendChild(newItem);

    inputField.value = "";
}