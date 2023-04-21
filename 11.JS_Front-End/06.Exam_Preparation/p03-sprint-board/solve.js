function attachEvents() {
    const URL = "http://localhost:3030/jsonstore/tasks/";
    let taskState = {
        status: {
            "ToDo": "Move to In Progress",
            "In Progress": "Move to Code Review",
            "Code Review": "Move to Done",
            "Done": "Close"
        },
        container: {
            "ToDo": document.querySelector("#todo-section > .task-list"),
            "In Progress": document.querySelector("#in-progress-section > .task-list"),
            "Code Review": document.querySelector("#code-review-section > .task-list"),
            "Done": document.querySelector("#done-section > .task-list")
        }
    };

    let loadBtn = document.getElementById("load-board-btn");
    loadBtn.addEventListener("click", (e) => loadTasks(e));

    let createBtn = document.getElementById("create-task-btn");
    createBtn.addEventListener("click", (e) => addTask(e));


    function moveTask(e) {

        let destination = e.currentTarget.textContent.substring(8);

        if (destination === "") {
            deleteTask(e.currentTarget.id);
            return;
        }

        let taskItem = e.currentTarget.parentNode;
        let updatedTask = {
            title: taskItem.children[0].textContent,
            description: taskItem.children[1].textContent,
            status: destination
        };

        let httpContext = {
            method: "PATCH",
            body: JSON.stringify(updatedTask)
        };

        fetch(URL + e.currentTarget.id, httpContext)
            .then(loadTasks)
            .catch(err => console.log(err));
    }

    function deleteTask(id) {
        fetch(URL + id, {method: "DELETE"})
            .then(loadTasks)
            .catch(err => console.log(err));
    }

    function addTask(e) {
        e?.preventDefault();

        let titleTag = document.getElementById("title");
        let descriptionTag = document.getElementById("description")

        let newTask = {title: titleTag.value, description: descriptionTag.value, status: "ToDo"};
        let httpContext = {method: "POST", body: JSON.stringify(newTask)};

        fetch(URL, httpContext)
            .then(() => {
                titleTag.value = "";
                descriptionTag.value = "";
                loadTasks();
            })
            .catch(err => console.log(err));
    }

    function loadTasks(e) {
        // e?.preventDefault();

        Object.values(taskState.container)
            .forEach(v => v.innerHTML = "");

        fetch(URL)
            .then(resp => resp.json())
            .then(j => {
                Object.values(j).forEach(v => loadTask(v));
            })
            .catch(err => console.log(err));

        function loadTask(tasks) {
            let {title, description, status, _id} = tasks;

            let container = taskState.container[status];

            let taskItem = document.createElement("li");
            taskItem.classList.add("task");
            container.appendChild(taskItem);

            let titleTag = document.createElement("h3");
            titleTag.textContent = title;
            taskItem.appendChild(titleTag);

            let descriptionTag = document.createElement("p");
            descriptionTag.textContent = description;
            taskItem.appendChild(descriptionTag);

            let buttonTag = document.createElement("button");
            buttonTag.textContent = taskState.status[status];
            buttonTag.id = _id;
            buttonTag.addEventListener("click", (e) => moveTask(e));
            taskItem.appendChild(buttonTag);
        }
    }
}


attachEvents();