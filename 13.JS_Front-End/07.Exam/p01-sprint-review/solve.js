function sprintReview(input) {
    let assigneesByTasks = {};
    let statusesByPoints = {
        "Done": 0, "Rest": 0
    };

    // let commands = {
    //     "Add New": addNew(components),
    //     "Change Status": changeStatus(components),
    //     "Remove Task": removeTask(components)
    // };

    let n = input.shift();

    for (let i = 0; i < n; i++) {
        let [assignee, taskId, title, status, estimatedPoints] = input[i].split(":");

        if (!assigneesByTasks.hasOwnProperty(assignee)) {
            assigneesByTasks[assignee] = {};
        }
        assigneesByTasks[assignee][taskId] = {title, status, estimatedPoints};
        if (status === "Done") {
            statusesByPoints["Done"] = statusesByPoints["Done"] + Number(estimatedPoints);
        } else {
            statusesByPoints["Rest"] = statusesByPoints["Rest"] + Number(estimatedPoints);
        }
    }


    for (let i = n; i < input.length; i++) {
        let components = input[i].split(":");
        let command = components.shift();
        switch (command) {
            case "Add New":
                addNew(components);
                break;
            case "Change Status":
                changeStatus(components);
                break;
            case "Remove Task":
                removeTask(components);
                break;
        }
    }

    let obj = {
        "ToDo": 0,
        "In Progress": 0,
        "Code Review": 0
    };

    Object.entries(assigneesByTasks)
        .forEach(ass => {
            let tasks = ass[1];
            let entries = Object.entries(tasks);
            entries.forEach(t =>
                obj[t[1].status] = obj[t[1].status] + Number(t[1].estimatedPoints));
        });


    console.log("ToDo: " + obj["ToDo"] + "pts" + "\n" +
        "In Progress: " + obj["In Progress"] + "pts" + "\n" +
        "Code Review: " + obj["Code Review"] + "pts" + "\n" +
        "Done Points: " + statusesByPoints["Done"] + "pts");

    let t = Number(statusesByPoints["Done"]) - Number(obj["ToDo"]) - Number(obj["In Progress"]) - Number(obj["Code Review"]);
    console.log(t < 0 ? "Sprint was successful!" : "Sprint was unsuccessful...");

    function addNew(components) {
        let [assignee, taskId, title, status, estimatedPoints] = components;

        if (!assigneesByTasks.hasOwnProperty(assignee)) {
            console.log("Assignee " + assignee + " does not exist on the board!")
        } else {
            assigneesByTasks[assignee][taskId] = {title, status, estimatedPoints};
            if (status === "Done") {
                statusesByPoints["Done"] = statusesByPoints["Done"] + Number(estimatedPoints);
            } else {
                statusesByPoints["Rest"] = statusesByPoints["Rest"] + Number(estimatedPoints);
            }
        }
    }

    function changeStatus(components) {
        let [assignee, taskId, newStatus] = components;

        if (!assigneesByTasks.hasOwnProperty(assignee)) {
            console.log("Assignee " + assignee + " does not exist on the board!")
        } else if (!assigneesByTasks[assignee].hasOwnProperty(taskId)) {
            console.log("Task with ID " + taskId + " does not exist for " + assignee)
        } else {
            assigneesByTasks[assignee][taskId].status = newStatus;

            if (newStatus === "Done") {
                statusesByPoints["Done"] = statusesByPoints["Done"] + Number(assigneesByTasks[assignee][taskId].estimatedPoints);
                statusesByPoints["Rest"] = statusesByPoints["Rest"] - Number(assigneesByTasks[assignee][taskId].estimatedPoints);
            }
        }
        //     :{assignee}{taskId}:{newStatus}
    }

    function removeTask(components) {
        let [assignee, index] = components;

        if (!assigneesByTasks.hasOwnProperty(assignee)) {
            console.log("Assignee " + assignee + " does not exist on the board!")
        } else {
            let e = Object.entries(assigneesByTasks[assignee]);

            if (index < 0 || index >= e.length) {
                console.log("Index is out of range!");
            } else {
                let estimatedPoints = assigneesByTasks[assignee][e[index][0]].estimatedPoints;
                let status = assigneesByTasks[assignee][e[index][0]].status;
                delete assigneesByTasks[assignee][e[index]]

                if (status === "Done") {
                    statusesByPoints["Done"] = statusesByPoints["Done"] + Number(estimatedPoints);
                } else {
                    statusesByPoints["Rest"] = statusesByPoints["Rest"] + Number(estimatedPoints);
                }
            }
        }
        //     :{assignee}{index}":
    }

//     "{assignee}:{taskId}:{title}:{status}:{estimatedPoints}
}

sprintReview([
        '5',
        'Kiril:BOP-1209:Fix Minor Bug:ToDo:3',
        'Mariya:BOP-1210:Fix Major Bug:In Progress:3',
        'Peter:BOP-1211:POC:Code Review:5',
        'Georgi:BOP-1212:Investigation Task:Done:2',
        'Mariya:BOP-1213:New Account Page:In Progress:13',
        'Add New:Kiril:BOP-1217:Add Info Page:In Progress:5',
        'Change Status:Peter:BOP-1290:ToDo',
        'Remove Task:Mariya:1',
        'Remove Task:Joro:1',
    ]
);