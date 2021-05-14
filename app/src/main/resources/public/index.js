'use strict'
const main = async () => {
    var getUsername = await fetch("/currentuser");
    var username = await getUsername.text();
    if (username) {
        document.querySelector("#userHUD").innerHTML = username;
        document.querySelector('#loginForm').style.visibility="hidden";
    }else{
        document.querySelector('#navLogout').style.visibility="hidden";
        document.querySelector("#captchaDiv").innerHTML = '<img src="/captcha" title="captcha" id="captchaImg" onclick="reloadCap(this)" />';
    }
}
window.onload = main;

const reloadCap = async (caller) => {
    // force reload with useless query https://stackoverflow.com/a/5161034/15588806
    caller.src = "/captcha?rand=" + Math.random();

    console.log('changed captcha');
}

const getSecret = async ()=>{
    var getSec = await fetch("/secret");
    var sec = await getSec.text();
    alert(`Message received: ${sec}`)
}
