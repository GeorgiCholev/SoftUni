function solve() {
    let inputSentences = document.getElementById("input").value.split(".");
    inputSentences.pop();

    let output = document.getElementById("output");

    while (inputSentences.length > 0) {
        let paragraphContent = inputSentences.splice(0, 3);
        let paragraph = document.createElement("p");
        paragraph.textContent = paragraphContent.join(".") + ".";

        output.appendChild(paragraph);
    }
}