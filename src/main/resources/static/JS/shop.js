document.addEventListener("DOMContentLoaded", function () {
    const marketItemsContainer = document.querySelector(".market-items");

    // Fetch product data from the server
    fetch("/FarmersProduct/getProduct") // Replace with your actual API endpoint
        .then(response => response.json())
        .then(products => {
            marketItemsContainer.innerHTML = ""; // Clear existing items
            console.log(products);
            products.forEach(product => {
                const productCard = document.createElement("div");
                productCard.classList.add("market-item");

                productCard.innerHTML = `
                    <div class="item-image-container">
                        <img src="${"/FarmersProduct/"+product.imagePath+".jpg"
                            }" alt="${product.name}" class="item-image">
                        <div class="item-category">${product.categories}</div>
                    </div>
                    <div class="item-details">
                        <div class="item-name">${product.productName}</div>
                        <div class="item-price">₹${product.price}/${product.unit}</div>
                                  <div class="item-quantity">Available: ${product.productQuantity} ${product.unit}</div>
                                   <div class="item-quantity">Minimum Order: ${product.minimumOrder} ${product.unit}</div>
                        <div class="item-meta">
                            <div class="item-seller">
                                <span>👨‍🌾</span> ${product.seller}
                            </div>
                            <div>${product.location}</div>
                        </div>
                        <div class="item-contact">
                            <button class="contact-btn">
                                <span>📞</span> Contact
                            </button>
                            <button class="contact-btn">
                                <span>🛒</span> Buy
                            </button>
                        </div>
                    </div>
                `;
                marketItemsContainer.appendChild(productCard);
            });
        })
        .catch(error => console.error("Error fetching products:", error));
});




     // <div class="item-quantity">Available: ${product.quantity} ${product.unit}</div>