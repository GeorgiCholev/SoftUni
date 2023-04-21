function focused() {
    Array.from(document.getElementsByTagName("input"))
        .forEach(input => {
            input.addEventListener("focus", (e) => {
                e.target.parentNode.classList.add("focused");
            })
            input.addEventListener("blur", (e) => {
                e.target.parentNode.classList.remove("focused");
            })
        });
}