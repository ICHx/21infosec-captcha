'use strict'
const main = () => {
}
window.onload = main;

const reloadCap = async(caller)=>{
    // force reload with useless query https://stackoverflow.com/a/5161034/15588806
    caller.src="/captcha?rand="+Math.random();
    
    console.log('changed captcha');
    
}