function addItem() {
    let optionText = document.getElementById("newItemText");
    let optionValue = document.getElementById("newItemValue");

    let option = document.createElement("option");
    option.textContent = optionText.value;
    option.value = optionValue.value;

    let select = document.getElementById("menu");
    select.appendChild(option);

    optionText.value = "";
    optionValue.value = "";
}