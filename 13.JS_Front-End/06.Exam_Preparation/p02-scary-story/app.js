window.addEventListener("load", solve);

function solve() {
    let elements = {
        inputFields: {
            firstName: document.getElementById("first-name"),
            lastName: document.getElementById("last-name"),
            age: document.getElementById("age"),
            storyTitle: document.getElementById("story-title"),
            genre: document.getElementById("genre"),
            story: document.getElementById("story"),
        },
        publishButton: document.getElementById("form-btn"),
        previewList: document.getElementById("preview-list"),
        main: document.getElementById("main"),
        listItem: null
    };

    let storyFields = {};

    elements.publishButton.addEventListener("click", publishStory);

    function publishStory() {

        let emptyFieldsCount = Object.entries(elements.inputFields)
            .filter(e => e[1].value.trim() === "")
            .length;

        if (emptyFieldsCount !== 0) {
            return;
        }

        for (const key in elements.inputFields) {
            storyFields[key] = elements.inputFields[key].value;
            elements.inputFields[key].value = "";
        }

        let [firstName, lastName, age, storyTitle, genre, story] = Object.values(storyFields);

        elements.listItem = createElement("li", elements.previewList, "", ["story-info"]);
        let article = createElement("article", elements.listItem, "");

        createElement("h4", article, "Name: " + firstName + " " + lastName);
        createElement("p", article, "Age: " + age);
        createElement("p", article, "Title: " + storyTitle);
        createElement("p", article, "Genre: " + genre);
        createElement("p", article, story);

        let saveBtn = createElement("button", elements.listItem, "Save story", ["save-btn"]);
        saveBtn.addEventListener("click", saveStory);

        let editBtn = createElement("button", elements.listItem, "Edit story", ["edit-btn"]);
        editBtn.addEventListener("click", () => editStory(storyFields));

        let deleteBtn = createElement("button", elements.listItem, "Delete story", ["delete-btn"]);
        deleteBtn.addEventListener("click", () => deleteStory(storyFields));

        elements.publishButton.disabled = true;
    }

    function deleteStory(fields) {
        elements.publishButton.disabled = false;
        elements.previewList.removeChild(elements.listItem);
        elements.listItem = null;

        return fields;
    }

    function editStory(fields) {
        deleteStory(fields);

        for (const key in fields) {
            elements.inputFields[key].value = fields[key];
        }
    }

    function saveStory() {
        debugger;
        elements.main.innerHTML = "";
        createElement("h1", elements.main, "Your scary story is saved!");
    }

    function createElement(type, parent, content, classes, attributes, id) {
        let element = document.createElement(type);

        parent.appendChild(element);

        if (content) {
            if (type === "input") {
                element.value = content
            } else {
                element.textContent = content;
            }
        }

        if (classes && classes.length > 0) {
            element.classList.add(...classes);
        }

        if (attributes) {
            Object.entries(attributes)
                .forEach(e => element.setAttribute(e[0], e[1]));
        }

        if (id) {
            element.id = id;
        }

        return element;
    }
}

