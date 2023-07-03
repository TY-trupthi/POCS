let productArray = [
    {
        name : "Pen",
        price : 13
    },
    {
        name : "Pencil",
        price : 12
    }
];

// productArray.forEach( product =>
//     product["discount"] = 2

// )

productArray.map(
    product => {
    product["discount"] = 2
    return product;
    }
)

productArray = productArray.filter(product => product.name == "Pen" && product.price == 12);

productArray.includes()

console.log(productArray);

//

let myNumber = [1,2,3,4];

console.log(myNumber.slice(1,2));

console.log(myNumber.indexOf(2));




