const studentNumber = document.getElementById("studentNumber");
const isAutoInc = document.getElementById("isAutoInc");

const changeDisabled = () => {
    studentNumber.readOnly = !studentNumber.readOnly;
}

isAutoInc.addEventListener("change", changeDisabled);
