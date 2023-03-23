function addItem() {
    let ulContainer = document.getElementById("items");

    let inputField = document.getElementById("newItemText");
    let newItemText = inputField.value;
    let newItem = document.createElement("li");
    newItem.textContent = newItemText;

    ulContainer.appendChild(newItem);

    inputField.value = "";
}