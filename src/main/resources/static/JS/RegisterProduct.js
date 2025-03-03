const uploadArea = document.getElementById('uploadArea');
const fileInput = document.getElementById('productImage');
const imagePreview = document.getElementById('imagePreview');
const productForm = document.getElementById('productForm');
const successMessage = document.getElementById('successMessage');


uploadArea.addEventListener('click', () => {
    fileInput.click();
});

uploadArea.addEventListener('dragover', (e) => {
    e.preventDefault();
    uploadArea.style.backgroundColor = 'rgba(165, 214, 167, 0.2)';
});

uploadArea.addEventListener('dragleave', () => {
    uploadArea.style.backgroundColor = '';
});

uploadArea.addEventListener('drop', (e) => {
    e.preventDefault();
    uploadArea.style.backgroundColor = '';

    if (e.dataTransfer.files.length) {
        fileInput.files = e.dataTransfer.files;
        displayPreview(e.dataTransfer.files[0]);
    }
});

fileInput.addEventListener('change', () => {
    if (fileInput.files.length) {
        displayPreview(fileInput.files[0]);
    }
});

function displayPreview(file) {
    const reader = new FileReader();

    reader.onload = (e) => {
        imagePreview.src = e.target.result;
        imagePreview.style.display = 'block';
    };

    reader.readAsDataURL(file);
}

// Form submission
// productForm.addEventListener('submit', (e) => {
//     e.preventDefault();

//     // Check required fields
//     const productName = document.getElementById('productName').value;
//     const productPrice = document.getElementById('productPrice').value;

//     if (!productName || !productPrice || !fileInput.files.length) {
//         alert('Please fill all required fields and upload an image');
//         return;
//     }

//     // Create FormData to send as multipart/form-data
//     let obj={
//         productName: productName,
//         productPrice: productPrice,
//     }
//     const formData = new FormData();
//     formData.append('FarmersProduct',JSON.stringify(obj));
//     formData.append('productImage', fileInput.files[0]);

//     // Use fetch to send a POST request with FormData
//     fetch('/FarmersProduct/registerProduct', {
//         method: 'POST',
//         body: formData // Directly send FormData
//     })
//     .then(response => response.json()) // Parse JSON response
//     .then(data => {
//         console.log('Success:', data);
//         alert('Form submitted successfully!');
//     })
//     .catch(error => {
//         console.error('Error:', error);
//         alert('An error occurred while submitting the form.');
//     });

//     // Show success message (in a real app, this would be after API submission)
//     successMessage.style.display = 'flex';

//     // Reset form after submission
//     productForm.reset();
//     imagePreview.style.display = 'none';
//     // successMessage.style.display = 'none';
// });



// Form submission
productForm.addEventListener('submit', (e) => {

    e.preventDefault();
 
    // Check required fields
    const productName = document.getElementById('productName').value;
    const productPrice = document.getElementById('productPrice').value;
    const category = document.getElementById('productCategory').value;
    const unit = document.getElementById('Unit').value;
    const productQuantity= document.getElementById('productQuantity').value;
    const minimumOrder= document.getElementById('minimumOrder').value;
    // const productDescription= document.getElementById('productDescription').value;
    
    


    console.log(category);
    if (!productName || !productPrice || !fileInput.files.length) {
        alert('Please fill all required fields and upload an image');
        return;
    }

    // Create the object to send as JSON
    const farmersProduct = {
        productName: productName,
        price: productPrice,
        categories: category,
        uploadedContact:localStorage.getItem('email'),
        unit:unit,
        productQuantity:productQuantity,
        minimumOrder:minimumOrder,
    };

    // Create FormData to send as multipart/form-data
    const formData = new FormData();
    formData.append('farmersProduct', JSON.stringify(farmersProduct));  // Match the parameter name here
    formData.append('productImage', fileInput.files[0]);

    // Use fetch to send a POST request with FormData
    fetch('/FarmersProduct/registerProduct', {
        method: 'POST',
        body: formData // Directly send FormData
    })
    .then(response => response.json()) // Parse JSON response
    .then(data => {
        console.log('Success:', data);
        alert('Form submitted successfully!');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while submitting the form.');
    });

    // Show success message (in a real app, this would be after API submission)
    successMessage.style.display = 'flex';

    // Reset form after submission
    productForm.reset();
    imagePreview.style.display = 'none';
});


document.addEventListener("DOMContentLoaded", function () {
    const menuBtn = document.querySelector(".mobile-menu-btn");
    const navLinks = document.querySelector(".nav-links");

    menuBtn.addEventListener("click", function () {
        navLinks.classList.toggle("active"); // Toggle the active class
    });
});
