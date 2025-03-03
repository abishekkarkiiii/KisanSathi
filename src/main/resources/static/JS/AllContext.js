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
productForm.addEventListener('submit', (e) => {
    e.preventDefault();

    // Check required fields
    const productName = document.getElementById('productName').value;
    const productPrice = document.getElementById('productPrice').value;

    if (!productName || !productPrice || !fileInput.files.length) {
        alert('Please fill all required fields and upload an image');
        return;
    }

    // Show success message (in a real app, this would be after API submission)
    successMessage.style.display = 'flex';

    // Add the new product to the market items (for demo purposes)
    setTimeout(() => {
        const marketItems = document.querySelector('.market-items');
        const newItem = document.createElement('div');
        newItem.className = 'market-item';

        const category = document.getElementById('productCategory').value || 'Other';
        const quantity = document.getElementById('productQuantity').value || '1';

        newItem.innerHTML = `
            <div class="item-image-container">
                <img src="${imagePreview.src}" alt="${productName}" class="item-image">
                <div class="item-category">${category}</div>
            </div>
            <div class="item-details">
                <div class="item-name">${productName}</div>
                <div class="item-price">₹${productPrice}</div>
                <div class="item-quantity">Available: ${quantity} units</div>
                <div class="item-meta">
                    <div class="item-seller">
                        <span>👨‍🌾</span> You
                    </div>
                    <div>Your Location</div>
                </div>
                <div class="item-contact">
                    <button class="contact-btn">
                        <span>📞</span> Contact
                    </button>
                    <button class="contact-btn">
                        <span>🛒</span> Buy Now
                    </button>
                </div>
            </div>
        `;

        marketItems.prepend(newItem);

        // Reset form
        productForm.reset();
        imagePreview.style.display = 'none';
        successMessage.style.display = 'none';
    }, 2000);
});

// Filter buttons
const filterButtons = document.querySelectorAll('.filter-button');

filterButtons.forEach(button => {
    button.addEventListener('click', () => {
        // Remove active class from all buttons
        filterButtons.forEach(btn => btn.classList.remove('active'));

        // Add active class to clicked button
        button.classList.add('active');

        // In a real app, this would filter the products
    });
});