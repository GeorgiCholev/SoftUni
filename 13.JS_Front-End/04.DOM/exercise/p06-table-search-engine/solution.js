function solve() {
   document.querySelector('#searchBtn').addEventListener('click', onClick);
   let searchField = document.getElementById("searchField");
   let rows = Array.from(document.querySelectorAll("tbody > tr"));

   function onClick() {

      let searchString = searchField.value;
      for (const row of rows) {
         let rowText = row.textContent.trim();

         if (row.classList.contains("select")) {
            row.classList.remove("select");
         }

         if (rowText.includes(searchString)) {
            row.classList.add("select");
         }

         searchField.value = "";
      }
   }
}