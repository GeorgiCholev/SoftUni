function attachEvents() {
    const URL = "http://localhost:3030/jsonstore/tasks/";
    let toDoList = document.getElementById("todo-list");

    let addBtn = document.getElementById("add-button");
    addBtn.addEventListener("click", (e) => addTask(e));
    let loadBtn = document.getElementById("load-button");
    loadBtn.addEventListener("click", (e) => loadAll(e));

    function addTask(e) {
        e?.preventDefault();

        let inputField = document.getElementById("title");
        let name = inputField.value;
        inputField.value = "";

        let httpContext = {
            method: "POST",
            body: JSON.stringify({name})
        };

        fetch(URL, httpContext)
            .then(initLoad)
            .catch(err => console.log(err));
    }

    function loadAll(e) {
        e?.preventDefault();
        initLoad();
    }

    function initLoad() {
        toDoList.innerHTML = "";
        fetch(URL)
            .then(resp => resp.json())
            .then(json => {
                Object.values(json)
                    .forEach(task => addTask(task))
            })
            .catch(err => console.log(err));

        function addTask(task) {
            let [name, id] = Object.values(task);

            let taskItem = document.createElement("li");
            taskItem.id = id;
            toDoList.appendChild(taskItem);

            let taskName = document.createElement("span");
            taskName.textContent = name;
            taskItem.appendChild(taskName);

            let removeBtn = document.createElement("button");
            removeBtn.textContent = "Remove";
            removeBtn.addEventListener("click", (e) => removeTask(e));
            taskItem.appendChild(removeBtn);

            let editBtn = document.createElement("button")
            editBtn.textContent = "Edit";
            editBtn.addEventListener("click", (e) => editTask(e));
            taskItem.appendChild(editBtn);
        }
    }

    function removeTask(e) {
        e?.preventDefault();

        console.log(e.currentTarget.parentNode.id);
        fetch(URL + e.currentTarget.parentNode.id, {method: "DELETE"})
            .then(initLoad)
            .catch(err => console.log(err));
    }

    function editTask(e) {
        e?.preventDefault();

        let editBtn = e.currentTarget;
        let taskItem = editBtn.parentNode;

        let editInputField = document.createElement("input");
        let spanName = taskItem.children[0];
        editInputField.value = spanName.textContent;
        taskItem.replaceChild(editInputField, spanName);

        let submitBtn = document.createElement("button");
        submitBtn.textContent = "Submit";
        submitBtn.addEventListener("click", (e) => patchTask(e))
        taskItem.replaceChild(submitBtn, editBtn);

        function patchTask(e) {
            e?.preventDefault();

            let httpContext = {
                method: "PATCH",
                body: JSON.stringify({name: editInputField.value})
            };

            fetch(URL + e.currentTarget.parentNode.id, httpContext)
                .then(initLoad)
                .catch(err => console.log(err));
        }
    }
}


attachEvents();
