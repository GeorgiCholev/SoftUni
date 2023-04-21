handle();

function handle() {
    const URL = "http://localhost:3030/jsonstore/tasks/";

    let coursesById = {};

    let coursesTag = document.getElementById("list");
    let loadBtn = document.getElementById("load-course");
    loadBtn.addEventListener("click", (e) => visualizeCourses(e));

    let form = {
        title: document.getElementById("course-name"),
        type: document.getElementById("course-type"),
        description: document.getElementById("description"),
        teacher: document.getElementById("teacher-name")
    };
    let addBtn = document.getElementById("add-course");
    addBtn.addEventListener("click", (e) => addCourse(e));

    let editCourseBtn = document.getElementById("edit-course");

    function deleteCourse(id) {
        let course = coursesById[id];
        let httpContext = {method: "DELETE", body: JSON.stringify(course)};

        fetch(URL + id, httpContext)
            .then(() => visualizeCourses())
            .catch(err => console.log(err));

        return course;
    }

    function addCourse(e) {
        let newCourse = {};

        Object.entries(form)
            .forEach(e => {
                newCourse[e[0]] = e[1].value;
                e[1].value = "";
            });

        let httpContext = {method: "POST", body: JSON.stringify(newCourse)};
        fetch(URL, httpContext)
            .then(() => visualizeCourses())
            .catch(err => console.log(err));
    }

    function visualizeCourses(e) {
        coursesTag.innerHTML = "";
        coursesById = {};

        fetch(URL)
            .then(resp => resp.json())
            .then(j => Object.values(j).forEach(v => visualizeEntry(v)))
            .catch(err => console.log(err));

        function visualizeEntry(entry) {
            let {title, type, description, teacher, _id} = entry;
            coursesById[_id] = {title, type, description, teacher};

            let container = document.createElement("div");
            container.classList.add("container");
            coursesTag.appendChild(container);

            let titleTag = document.createElement("h2");
            titleTag.textContent = title;
            container.appendChild(titleTag);

            let teacherTag = document.createElement("h3");
            teacherTag.textContent = teacher;
            container.appendChild(teacherTag);

            let typeTag = document.createElement("h3");
            typeTag.textContent = type;
            container.appendChild(typeTag);

            let descriptionTag = document.createElement("h4");
            descriptionTag.textContent = description;
            container.appendChild(descriptionTag);

            let editBtn = document.createElement("button");
            editBtn.textContent = "Edit Course";
            editBtn.classList.add("edit-btn");
            editBtn.addEventListener("click", (e) => editCourse(e, _id));
            container.appendChild(editBtn);

            let finishBtn = document.createElement("button");
            finishBtn.textContent = "Finish Course";
            finishBtn.classList.add("finish-btn");
            finishBtn.addEventListener("click", (e) => deleteCourse(_id));
            container.appendChild(finishBtn);

            function editCourse(e, id) {
                e.currentTarget.parentNode.parentNode.removeChild(e.currentTarget.parentNode);

                let course = coursesById[id];

                Object.entries(form)
                    .forEach(field => field[1].value = course[field[0]]);

                editCourseBtn.disabled = false;
                editCourseBtn.addEventListener("click", (e) => edit(e, id));
                addBtn.disabled = true;

                function edit(e, id) {

                    let courseToEdit = {};

                    Object.entries(form)
                        .forEach(field => {
                            courseToEdit[field[0]] = field[1].value
                            field[1].value = "";
                        });

                    let httpContext = {method: "PUT", body: JSON.stringify(courseToEdit)};
                    fetch(URL + id, httpContext)
                        .then(() => {
                            editCourseBtn.disabled = true;
                            addBtn.disabled = false;
                            visualizeCourses();
                        })
                        .catch(err => console.log(err));
                }
            }
        }
    }

}