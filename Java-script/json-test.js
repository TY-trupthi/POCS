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
let jsonString = JSON.stringify(productArray);
let jsonValue = JSON.parse(jsonString);
console.log(jsonValue[1].price);
