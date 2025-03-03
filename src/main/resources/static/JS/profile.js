document.addEventListener("DOMContentLoaded", async function () {
    let data = {
        email: localStorage.getItem('email')
    };

    const response = await fetch('/profile/profile-data', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    if (response.ok) {
        const profileData = await response.json();
        console.log("Profile data received from server:", profileData);

        // Update profile info on the page
        document.getElementById('profile-name').textContent = profileData.name;
        document.getElementById('profile-email').textContent = profileData.email;
        document.getElementById('profile-phone').textContent = profileData.phoneNumber;
        document.getElementById('profile-bio').textContent = profileData.qualification || 'No bio available';

        // If the user has a profile picture, update it
        if (profileData.picture) {
            document.getElementById('profile-pic').src = profileData.picture;
        } else {
            document.getElementById('profile-pic').src = 'https://via.placeholder.com/150'; // Default image if no profile picture
        }

    } else {
        console.error('Error fetching profile data');
    }
});


