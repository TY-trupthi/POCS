//spread operator
let favColor = ["blue"];

// to get only content ... is used
let colors = ["Red", "green", ...favColor];

console.log(colors);

// copying the array
let oldArray = [1,2,3];
let newArray = oldArray; // now newArray is also pointing to previous array
oldArray.pop(1);

console.log(newArray);

let copiedArray = [...oldArray]; // if we copy the array, those 2 will be independent of each other
newArray.pop(1);

console.log(newArray);

console.log(copiedArray);

//Rest operator

function testRest(...args){
    console.log(args);
}

testRest(1,2,3);