'use strict'
const main = () => {
}
window.onload = main;

const reloadCap = async()=>{
    var res = await fetch("/captcha");
    var img2 = await res.blob;
    
    console.log(img2);
    
}