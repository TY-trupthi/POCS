let sentence = "Hello world";

sentence = sentence.split(" ").map(word => word.charAt(0)).join(" ");

console.log(sentence);

//duplicate

let myArray = [1,9,2,3,9];

let newArray = {};

myArray.forEach(value => {
    if(newArray[value]){     
    newArray[value] =  newArray[value] + 1 ;
    }else{
        newArray[value] = 1;        
    }
});

for (const key in newArray) {
    if(newArray[key] >1){
        console.log(key)
    }
}

// sort
myArray.sort(function(a,b) {
   return a-b;
});

console.log(myArray);

//while loop
let value = 1;
while(value<5){
    console.log("Hello " +value);
    value += 1;
}



