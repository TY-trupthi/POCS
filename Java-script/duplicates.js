let myArray = [1,2,3,4, 4];

 let count = {};

 for(let i = 0; i< myArray.length ; i++){
    if(count[myArray[i]]){
        count[myArray[i]]  =  count[myArray[i]] + 1;
    }else{
        count[myArray[i]] = 1;
    }
 }

 for(let b in count) {
   if(count[b] >= 2){
    console.log(b + " is repeated " + count[b] + " times");
   }
 }