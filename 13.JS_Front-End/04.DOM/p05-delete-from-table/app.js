function deleteByEmail() {
    let resultDiv = document.getElementById("result");
    let emailToDelete = document.querySelector("body label input").value;

    for (const row of Array.from(document.querySelectorAll("#customers tbody tr"))) {
        if (row.children[1].textContent === emailToDelete) {
            row.parentNode.removeChild(row);
            resultDiv.textContent = "Deleted.";
            return;
        }
    }

    resultDiv.textContent = "Not found.";
}