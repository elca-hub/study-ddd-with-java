async function fetchTeamMember(teamId) {
    const token = document.querySelector('meta[name="_csrf"]').content; 
    const tokenType = document.querySelector('meta[name="_csrf_header"]').content;

    const headers = {
        "Content-Type": "application/json"
    };

    headers[tokenType] = token;

    try {
        const res = await fetch("http://localhost:8080/api/team/fetch", {
            method: "POST",
            headers,
            body: JSON.stringify({teamId}),
        });
        const data = await res.json();
        console.log(data);
    } catch (e) {
        console.log("error");
    }
}

function init () {
    const firstTargetClassName = "first-show";
    const firstShowEle = document.getElementsByClassName(firstTargetClassName)[0]; // 最初に表示される要素を取得
    if (typeof firstShowEle != undefined) {
        firstShowEle.click(); // クリックをシミュレート
        firstShowEle.classList.remove(firstTargetClassName);
    }
}

init();
