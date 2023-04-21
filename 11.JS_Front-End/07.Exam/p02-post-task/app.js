window.addEventListener("load", solve);

function solve() {
    let idCounter = 1;
    let tasksById = {};

    let taskFormFields = {
        title: document.getElementById("task-title"),
        category: document.getElementById("task-category"),
        content: document.getElementById("task-content")
    };
    let publishBtn = document.getElementById("publish-btn");
    publishBtn.addEventListener("click", e => publishTask(e));

    let reviewList = document.getElementById("review-list");
    let publishedList = document.getElementById("published-list");

    function publishTask(e) {
        let fieldValues = Object.values(taskFormFields)
            .map(field => field.value);

        if (fieldValues.some(v => v === "")) {
            return;
        }

        let taskId = idCounter++;
        tasksById[taskId] = {title: fieldValues[0], category: fieldValues[1], content: fieldValues[2]};

        Object.values(taskFormFields)
            .forEach(f => f.value = "");

        createTask(taskId);

        function createTask(taskId) {
            let taskItem = createElement("li", reviewList, "", ["rpost"]);
            let article = createElement("article", taskItem);
            createElement("h4", article, tasksById[taskId].title);
            createElement("p", article, "Category: " + tasksById[taskId].category);
            createElement("p", article, "Content: " + tasksById[taskId].content);

            let editBtn = createElement("button", taskItem, "EDIT", ["action-btn", "edit"]);
            editBtn.addEventListener("click", () => editTask(taskItem, taskId));
            let postBtn = createElement("button", taskItem, "POST", ["action-btn", "post"]);
            postBtn.addEventListener("click", () => postTask(taskItem))
        }
    }

    function editTask(taskItem, id) {
        Object.entries(taskFormFields)
            .forEach(e => e[1].value = tasksById[id][e[0]]);

        delete tasksById[id];
        taskItem.parentNode.removeChild(taskItem);
    }
    function postTask(taskItem) {
        taskItem.parentNode.removeChild(taskItem);

        taskItem.removeChild(taskItem.children[2]);
        taskItem.removeChild(taskItem.children[1]);

        publishedList.appendChild(taskItem);
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