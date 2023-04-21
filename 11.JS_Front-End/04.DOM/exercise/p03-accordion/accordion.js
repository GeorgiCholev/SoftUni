function toggle() {
    let button = document.getElementsByClassName("button")[0];
    let extraText = document.getElementById("extra");

    let buttonContent = "Less";
    let displayType = "block";

    if (button.textContent !== "More") {
        buttonContent = "More";
        displayType = "none";
    }

    button.textContent = buttonContent;
    extraText.style.display = displayType;
}