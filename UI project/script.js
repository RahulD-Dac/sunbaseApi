// Function to handle login form submission
document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Prevent form submission
    const formData = new FormData(event.target);
    const username = formData.get("username");
    const password = formData.get("password");
    login(username, password);
});

// Function to handle login
function login(username, password) {
    const url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
         const body = JSON.stringify({ "login_id": username, "password": password });
    const headers = {
        "Content-Type": "application/json"
    };

    fetch(url, {
        method: "POST",
        headers: headers,
        body: body
    })
    .then(response => response.json())
    .then(data => {
        const accessToken = data.access_token;
        // Save the access token in session storage for subsequent API calls
        sessionStorage.setItem("accessToken", accessToken);

        // Redirect to the dashboard after successful login
        window.location.href = "dashboard.html";
    })
    .catch(error => {
        document.getElementById("loginError").textContent = "Error: " + error.message;
        console.error(error);
    });
}
