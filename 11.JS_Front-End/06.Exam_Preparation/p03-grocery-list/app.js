function attachEvents() {
    const BASE_URL = "http://localhost:3030/jsonstore/grocery/";
    const htmlElements = {
        addBtn: document.getElementById("add-product"),
        updateBtn: document.getElementById("update-product"),
        loadBtn: document.getElementById("load-product"),
        contentTable: document.getElementById("tbody"),
        form: {
            product: document.getElementById("product"),
            count: document.getElementById("count"),
            price: document.getElementById("price")
        }
    };

    htmlElements.loadBtn.addEventListener("click", e => loadProducts(e));
    htmlElements.addBtn.addEventListener("click", e => addProduct(e));

    function updateProduct(e) {
        let productRow = e.currentTarget.parentNode.parentNode;

        let productId = e.currentTarget.parentNode.id;
        let [product, count, price] = Array.from(productRow.children).map(ch => ch.textContent);
        let updateProduct = {product, count, price};

        htmlElements.contentTable.removeChild(productRow);

        Object.entries(htmlElements.form)
            .forEach(input => input[1].value = updateProduct[input[0]]);

        htmlElements.updateBtn.addEventListener("click", (e) => patchProduct(e));
        htmlElements.updateBtn.disabled = false;
        htmlElements.addBtn.disabled = true;

        function patchProduct(e) {
            Object.entries(htmlElements.form)
                .forEach(e => {
                    updateProduct[e[0]] = e[1].value;
                    e[1].value = "";
                });

            let httpContext = {
                method: "PATCH",
                body: JSON.stringify(updateProduct)
            };

            fetch(BASE_URL + productId, httpContext)
                .then(() => {
                    fixButton();
                    loadProducts();
                })
                .catch(err => console.log(err));


            function fixButton() {
                htmlElements.updateBtn.disabled = true;
                let newUpdateBtn = htmlElements.updateBtn.cloneNode(true);
                htmlElements.updateBtn.parentNode.replaceChild(newUpdateBtn, htmlElements.updateBtn);
                htmlElements.updateBtn = newUpdateBtn;

                htmlElements.addBtn.disabled = false;
            }
            //     var old_element = document.getElementById("btn");
            // var new_element = old_element.cloneNode(true);
            // old_element.parentNode.replaceChild(new_element, old_element);
        }
    }


    function deleteProduct(e) {
        fetch(BASE_URL + e.currentTarget.parentNode.id, {method: "DELETE"})
            .then(() => loadProducts())
            .catch(err => console.log(err));
    }

    function addProduct(e) {
        e?.preventDefault();

        let newProduct = {};

        Object.entries(htmlElements.form)
            .forEach(e => {
                newProduct[e[0]] = e[1].value;
                e[1].value = "";
            });

        let httpContext = {
            method: "POST",
            body: JSON.stringify(newProduct)
        };

        fetch(BASE_URL, httpContext)
            .then(() => loadProducts())
            .catch(err => console.log(err));
    }

    function loadProducts(e) {
        e?.preventDefault();

        htmlElements.contentTable.innerHTML = "";

        fetch(BASE_URL)
            .then(resp => resp.json())
            .then(j => {
                Object.values(j)
                    .forEach(product => addProductRow(product))
            })
            .catch(err => console.log(err));

        function addProductRow(p) {
            let {product, count, price, _id: id} = p;

            let productRow = document.createElement("tr");
            htmlElements.contentTable.appendChild(productRow);

            let nameCell = document.createElement("td");
            nameCell.textContent = product;
            nameCell.classList.add("name");
            productRow.appendChild(nameCell);

            let countCell = document.createElement("td");
            countCell.textContent = count;
            countCell.classList.add("count-product");
            productRow.appendChild(countCell);

            let priceCell = document.createElement("td");
            priceCell.textContent = price;
            priceCell.classList.add("product-price");
            productRow.appendChild(priceCell);

            let btnCell = document.createElement("td");
            btnCell.classList.add("btn");
            btnCell.id = id;
            productRow.appendChild(btnCell);

            let updateBtn = document.createElement("button");
            updateBtn.textContent = "Update";
            updateBtn.classList.add("update");
            updateBtn.addEventListener("click", (e) => updateProduct(e));
            btnCell.appendChild(updateBtn);

            let deleteBtn = document.createElement("button");
            deleteBtn.textContent = "Delete";
            deleteBtn.classList.add("delete");
            deleteBtn.addEventListener("click", (e) => deleteProduct(e));
            btnCell.appendChild(deleteBtn);
        }
    }

}

attachEvents();