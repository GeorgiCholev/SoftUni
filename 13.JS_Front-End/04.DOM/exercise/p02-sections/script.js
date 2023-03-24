function create(words) {
   let content = document.getElementById("content");

   for (const word of words) {
      let container = document.createElement("div");
      let paragraph = setUpParagraph(word);
      container.appendChild(paragraph);
      container.addEventListener("click",
          (e) => e.target.children[0].style.display = "block");

      content.appendChild(container)
   }

   function setUpParagraph(word) {
      let paragraph = document.createElement("p");
      paragraph.style.display = "none";
      paragraph.textContent = word;
      return paragraph;
   }
}