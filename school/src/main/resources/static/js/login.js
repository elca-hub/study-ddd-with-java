const firstnameEle = document.getElementById("teacherFirstName");
const lastnameEle = document.getElementById("teacherLastName");
const usernameEle = document.getElementById("userName");

const changeUserName = () => {
    document.getElementById("userName").value = firstnameEle.value + " " + lastnameEle.value;
}

firstnameEle.addEventListener("input", changeUserName);

lastnameEle.addEventListener("input", changeUserName);
