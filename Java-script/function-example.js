function createUser(name, age){
 return {
    name : name,
    age : age,
    height : 5,
   }
}

let createdUser = createUser("Trupthi", 14)

//console.log("hello" in createUser)

// for(let keyword in createdUser){
//     console.log(keyword)
// }

let clone = {}; // the new empty object

// let's copy all user properties into it
// for (key in createdUser) {
//   clone[key] = createdUser[key];
// }

Object.assign(clone, createdUser);

createdUser.age = 100;

console.log(clone);

