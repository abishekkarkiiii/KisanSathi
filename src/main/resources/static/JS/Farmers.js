
document.addEventListener("DOMContentLoaded", async function () {
    let RegisterProfilecheck = document.getElementById('RegisterProfile');
    let doctorApproval = document.getElementById('doctorApproval');
    
    let data = {
        email: localStorage.getItem('email')
    };

    const response = await fetch('/profile/recognize', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });

    if (response.ok) {
        const profileData = await response.json();
        if(profileData.role=="Doctor"){
            RegisterProfilecheck.remove();
            doctorApproval.remove();
        }

        console.log("Profile data received from server:", profileData);
      
    } else {
        console.error('Error fetching profile data');
    }
});







