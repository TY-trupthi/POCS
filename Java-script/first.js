let firtName = null;
 console.log(typeof firtName);

 firtName = true;

 console.log(typeof firtName);

 let person = {};
 person.name = "Trupthi";
 person.age =5;

 for(let checking in person){
    console.log(checking);
 }

 console.log(person);

 let numbers = [];

 for(let i= 0; i<5; i++){
    numbers[i] = i;
 }

 numbers.forEach(element => {
    console.log(element + " element")
 });

 console.log(numbers);

 function addNumbers(a,b){
    return a+b;
 }

 console.log(addNumbers(1,2));




 









