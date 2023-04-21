function subtract() {
    let first = Number(document.getElementById("firstNumber").value)
    let second = Number(document.getElementById("secondNumber").value);

    let subtraction = first - second;
    document.getElementById("result").textContent = subtraction.toString();
}