const container = $(".container-product");
const containerFeatureProducts = $(".new-product-home-2-active");
const cSaleProduct = $(".feature-home-2-active");
fetch("http://localhost:8080/api/products/get-all-products")
    .then(res => res.json())
    .then(res => {
        const recommentProducts = res.data.slice(0, 4);
        const featureProducts = res.data.slice(4, 8);
        const saleProducts = res.data.slice(10,14);
        display(container, recommentProducts);
        display(containerFeatureProducts, featureProducts);
        display(cSaleProduct, saleProducts);

    })
    .catch(err => console.error(err));


function display(element, list) {
    // Clear the container before appending new items
    element.empty();


    list.map(item => {
        let html = `<div class="col-lg-3">
							<div  class="single-new-product">
								<div class="product-img">
									<a href="/product/${item.id}">
										<img src="${item.imageUrl}" class="first_img" alt="" />

									</a>
								</div>
								<div class="product-content text-center">
									<a href="/product/${item.id}"><h3>${item.name}</h3></a>
									<div class="product-price-star">
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star-o"></i>
										<i class="fa fa-star-o"></i>
									</div>
									<div class="price">
										<h4>${item.salePrice.toLocaleString('vi-VN')} ₫</h4>
									</div>
								</div>
								<div class="product-icon-wrapper">
									<div class="product-icon">
										<ul>
											<li><a href="#"><span class="lnr lnr-sync"></span></a></li>
											<li><a href="#"><span class="lnr lnr-heart"></span></a></li>
											<li><a href="#"><span class="lnr lnr-cart"></span></a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>`;
        element.append(html);
    });
}