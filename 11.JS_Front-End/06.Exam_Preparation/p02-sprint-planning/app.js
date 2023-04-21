window.addEventListener('load', solve);

function solve() {

    let util = {
        form: {
            title: document.getElementById("title"),
            description: document.getElementById("description"),
            label: document.getElementById("label"),
            points: document.getElementById("points"),
            assignee: document.getElementById("assignee"),
        },
        taskIdHidden: document.getElementById("task-id"),
        createBtn: document.getElementById("create-task-btn"),
        deleteBtn: document.getElementById("delete-task-btn"),
        section: document.getElementById("tasks-section"),
        totalPoints: document.getElementById("total-sprint-points"),
        taskCounter: 1,
        labels: {
            icons: {
                "Feature": "&#8865;",
                "Low Priority Bug": "&#9737;",
                "High Priority Bug": "&#9888;"
            },
            additionalClass: {
                "Feature": "feature",
                "Low Priority Bug": "low-priority",
                "High Priority Bug": "high-priority"
            }
        },
    };

    let tasksById = {};

    util.createBtn.addEventListener("click", (e) => createTask(e));


    function createTask(e) {
        let fields = Object.entries(util.form);

        if (fields.some(f => f[1].value === "")) {
            return;
        }

        let id = "task-" + util.taskCounter;
        util.taskCounter = util.taskCounter + 1;

        let task = {};
        for (const field of fields) {
            task[field[0]] = field[1].value;
            field[1].value = "";
        }

        tasksById[id] = task;

        handlePoints();

        let taskArticle = createElement(
            "article", util.section, null, ["task-card"], null, id
        );

        let labelContent = task.label + " " + util.labels.icons[task.label];
        let additionalClass = util.labels.additionalClass[task.label];
        let label = createElement("div", taskArticle, null, ["task-card-label", additionalClass]);
        label.innerHTML = labelContent;

        createElement("h3", taskArticle, task.title, ["task-card-title"]);
        createElement("p", taskArticle, task.description, ["task-card-description"]);
        createElement("div", taskArticle, "Estimated at: " + task.points + "pts", ["task-card-points"]);
        createElement("div", taskArticle, "Assigned to: " + task.points, ["task-card-assignee"]);

        let actionsContainer = createElement("div", taskArticle, null, ["task-card-actions"]);
        let deleteBtn = createElement("button", actionsContainer, "Delete");
        deleteBtn.addEventListener("click", (e) => deleteTask(e));

    }

    function handlePoints() {
        let points = Object.values(tasksById)
            .map(v => Number(v.points))
            .reduce((a,b) => a + b, 0);
        let textContent = util.totalPoints.textContent;
        let a = textContent.indexOf("pts");
        let b = textContent.indexOf("Points");
        util.totalPoints.textContent = textContent.substring(0, b + 7) + points + textContent.substring(a);
    }

    function deleteTask(e) {
        let taskId = e.currentTarget.parentNode.parentNode.id;

        Object.entries(tasksById[taskId])
            .forEach(e => {
                util.form[e[0]].value = e[1];
                util.form[e[0]].disabled = true;
            });

        util.deleteBtn.disabled = false;
        util.createBtn.disabled = true;
        util.taskIdHidden.value = taskId;

        util.deleteBtn.addEventListener("click", (e) => removeTask(e));

        function removeTask(e) {
            util.deleteBtn.disabled = true;
            util.createBtn.disabled = false;
            util.taskIdHidden.value = "";

            Object.entries(util.form)
                .forEach(e => {
                   e[1].disabled = false;
                   e[1].value = "";
                });

            let removedTask = document.getElementById(taskId);
            util.section.removeChild(removedTask);

            delete tasksById[taskId];
            handlePoints();
        }
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