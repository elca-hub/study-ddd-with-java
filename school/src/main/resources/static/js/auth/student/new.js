const studentNumber = document.getElementById("studentNumber");
const isAutoInc = document.getElementById("isAutoInc");

const changeDisabled = () => {
    studentNumber.disabled = !studentNumber.disabled;
}

isAutoInc.addEventListener("change", changeDisabled);
