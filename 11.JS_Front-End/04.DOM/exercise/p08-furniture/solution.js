function solve() {
    const [generateBtn, buyBtn] = Array.from(document.getElementsByTagName("button"));
    const [generateArea, buyArea] = Array.from(document.getElementsByTagName("textarea"));

    generateBtn.addEventListener("click", generateFromJson);
    buyBtn.addEventListener("click", buyCheckedItems);


    function buyCheckedItems() {
        const checkedInputs = Array.from(
            document.querySelectorAll("tbody tr input:checked")
        );

        const boughtFurniture = [];
        let totalPrice = 0;
        const decFactors = [];

        for (const input of checkedInputs) {
            const row = input.parentNode.parentNode;
            console.log(row);
            boughtFurniture.push(row.children[1].children[0].textContent);
            totalPrice += Number(row.children[2].children[0].textContent);
            decFactors.push(row.children[3].children[0].textContent);
        }

        buyArea.value = "Bought furniture: " + boughtFurniture.join(", ") + "\n" +
            "Total price: " + totalPrice.toFixed(2) + "\n" +
            "Average decoration factor: " + average(decFactors);

        function average(arr) {
            let avg = 0;
            for (const num of arr) {
                avg += Number(num);
            }
            return avg / arr.length;
        }
    }

    function generateFromJson() {
        const items = JSON.parse(generateArea.value)
        const tableBody = document.getElementsByTagName("tbody")[0];

        for (const item of items) {
            const row = createHtmlElement("tr", "", {}, tableBody)

            const imgTd = createHtmlElement("td", "", {}, row);
            createHtmlElement("img", "", {"src": item["img"]}, imgTd);

            const nameTd = createHtmlElement("td", "", {}, row);
            createHtmlElement("p", item["name"], {}, nameTd);

            const priceTd = createHtmlElement("td", "", {}, row);
            createHtmlElement("p", item["price"], {}, priceTd);

            const decFactorTd = createHtmlElement("td", "", {}, row);
            createHtmlElement("p", item["decFactor"], {}, decFactorTd);

            const checkedTd = createHtmlElement("td", "", {}, row);
            const checkbox = createHtmlElement("input", "", {type: "checkbox"}, checkedTd);
        }
    }


    function createHtmlElement(type, content, attributes, parent) {
        const htmlElement = document.createElement(type);

        htmlElement.textContent = content;


        Object.entries(attributes)
            .forEach(e => htmlElement.setAttribute(e[0], e[1]));

        parent.appendChild(htmlElement);

        return htmlElement;
    }
}