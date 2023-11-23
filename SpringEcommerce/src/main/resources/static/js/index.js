document.addEventListener("DOMContentLoaded", function() {
    const containerProducts = $(".products");
    console.log(containerProducts.html());

    fetch("http://localhost:8080/api/products/get-all-products")
        .then(res => res.json())
        .then(res => {
            // Clear the container before appending new items
            //containerProducts.empty();

            const recommentProducts = res.data.slice(0, 5);
            recommentProducts.map(item => {
                let html = `<article style="float: left; list-style: none; position: relative; width: 235px;" class="bx-clone">
                            <img src="${item.imageUrl}" alt="">
                            <h3>${item.name}</h3>
                            <h4>${item.salePrice.toLocaleString('vi-VN')} ₫</h4>
                            <a href="cart.html" class="btn-add">Add to cart</a>
                        </article>`;
                containerProducts.append(html);
            });
        })
        .catch(err => console.error(err));
});