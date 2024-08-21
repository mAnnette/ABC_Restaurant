document.addEventListener("DOMContentLoaded", function () {
    // Example of form validation
    const registrationForm = document.getElementById("registrationForm");
    if (registrationForm) {
        registrationForm.addEventListener("submit", function (event) {
            const username = document.getElementById("username").value.trim();
            const password = document.getElementById("password").value.trim();

            // Simple validation
            if (username === "" || password === "") {
                event.preventDefault(); // Prevent form submission
                alert("Username and Password are required!");
            }
        });
    }
    
    // Example AJAX for searching services
    // This is a placeholder for any AJAX request you might perform.
    const searchButton = document.getElementById("searchServices");
    if (searchButton) {
        searchButton.addEventListener("click", function () {
            const query = document.getElementById("searchInput").value;
            // Placeholder for search functionality (send AJAX request)
            console.log("Searching for services with query:", query);
            // Use fetch or XMLHttpRequest to get search results via API
        });
    }
	
	// Role-based branch dropdown visibility
   const roleSelect = document.getElementById("role");
   const branchGroup = document.getElementById("branchGroup");

   if (roleSelect && branchGroup) {
       roleSelect.addEventListener("change", function () {
           if (this.value === "staff") {
               branchGroup.style.display = "block";
           } else {
               branchGroup.style.display = "none";
           }
       });

       // Trigger change event on page load to set the correct state
       roleSelect.dispatchEvent(new Event('change'));
   }
	
	
	
});
