window.addEventListener('load', solve);

function solve() {

    let BASIC_IMG = "./static/img/img.png";

    let htmlElements = {
        inputFields: {
            genre: document.getElementById("genre"),
            name: document.getElementById("name"),
            author: document.getElementById("author"),
            date: document.getElementById("date")
        },
        addBtn: document.getElementById("add-btn"),
        allHitsContainer: document.querySelector("#all-hits > .all-hits-container"),
        likesCounterContainer: document.querySelector("#total-likes > .likes"),
        savedHitsContainer: document.querySelector("#saved-hits > .saved-container")
    };

    htmlElements.addBtn.addEventListener("click", (e) => addHit(e));

    function addHit(e) {
        e.preventDefault();

        if (Object.values(htmlElements.inputFields).some(f => f.value === "")) {
            return;
        }

        let hitContainer = createHtmlElement(
            "div", htmlElements.allHitsContainer, "", ["hits-info"]
        );

        createHtmlElement("img", hitContainer, "", null, {"src": BASIC_IMG});
        createHtmlElement("h2", hitContainer, "Genre: " + htmlElements.inputFields.genre.value);
        createHtmlElement("h2", hitContainer, "Name: " + htmlElements.inputFields.name.value);
        createHtmlElement("h2", hitContainer, "Author: " + htmlElements.inputFields.author.value);
        createHtmlElement("h3", hitContainer, "Date: " + htmlElements.inputFields.date.value);

        createHitButton("Save song", ["save-btn"], "saveBtn", (e) => saveHit(e));
        createHitButton("Like song", ["like-btn"], "likeBtn", (e) => likeHit(e));
        createHitButton("Delete", ["delete-btn"], "deleteBtn", (e) => deleteHit(e));

        Object.values(htmlElements.inputFields)
            .forEach(f => f.value = "");

        function createHitButton(content, classes, type, eventFunction) {
            htmlElements[type] = createHtmlElement("button", hitContainer, content, classes);
            htmlElements[type].addEventListener("click", e => eventFunction(e));
        }

    }

    function saveHit(e) {
        // e?.preventDefault();

        let hitContainer = e.currentTarget.parentNode;
        htmlElements.allHitsContainer.removeChild(hitContainer);
        console.log(hitContainer.children);
        hitContainer.removeChild(hitContainer.children[6]);
        hitContainer.removeChild(hitContainer.children[5]);

        htmlElements.savedHitsContainer.appendChild(hitContainer);
    }

    function likeHit(e) {
        // e?.preventDefault();

        let likeCounter = htmlElements.likesCounterContainer.children[0];

        let textComponents = likeCounter.textContent.split(": ");

        let increment = Number(textComponents[1]) + 1;
        likeCounter.textContent = textComponents[0] + ": " + increment;

        e.currentTarget.disabled = true;
    }

    function deleteHit(e) {
        // e?.preventDefault();

        e.currentTarget.parentNode.parentNode.removeChild(e.currentTarget.parentNode);
    }

    function createHtmlElement(type, parentElement, content, classes, attributes, id) {
        const htmlElement = document.createElement(type);
        parentElement.appendChild(htmlElement);

        if (content !== "") {
            if (type === "input" || type === "textarea") {
                htmlElement.value = content;
            } else {
                htmlElement.textContent = content;
            }
        }

        if (classes) {
            htmlElement.classList.add(...classes);
        }

        if (attributes) {
            Object.entries(attributes)
                .forEach(a => htmlElement.setAttribute(a[0], a[1]));
        }

        if (id) {
            htmlElement.id = id;
        }

        return htmlElement;
    }
}