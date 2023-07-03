// literal way

let literalPerson = {
    name : "Trupthi",
    age : 5
}

console.log(literalPerson.name + " literalPerson");

// using function

function getPerson(name, age){
   return {
    name,
   age
   };
}

let functionPerson = getPerson("Trupthi", 5);

console.log(functionPerson.name+ " functionPerson");

//using constructor

function Person(name, age){
   this.name = name;
   this.age = age;
}

let constructorPerson = new Person("Trupthi", 5);

console.log(constructorPerson.name + " constructorPerson using new keyword");

let callPerson = Person.call({}, "Trupthi", 1);

console.log(callPerson + " CallPerson");

//iterating object

let objectWithFunction = {
    firstName : "Trupthi",
    lastName : "kale",
    fullName : function (){
        return this.firstName + " " + this.lastName;
    }
}

for (let key in objectWithFunction) {
    //to avoid printing the function
    if(typeof objectWithFunction[key] != 'function'){
        console.log(key);
    }  
}

// to get all the key but we cannot seperate the function

let allTheKeys  = Object.keys(objectWithFunction);

console.log(allTheKeys);

// to check if the key exsists

if('fullName' in objectWithFunction){
    console.log("Present")
}
