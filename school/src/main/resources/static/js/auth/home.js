function createStudentElements(students) {
    const fistElements = `
    <table class="table">
        <thead>
          <tr>
            <th scope="col">生徒番号</th>
            <th scope="col">姓</th>
            <th scope="col">名</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
    `;
    const endElements = `
        </tbody>
    </table>
    `;

    let columnElements = "";

    for (const student of students) {
        columnElements += `
        <tr>
            <th scope="row">${student.studentNumber}</th>
            <td>${student.firstname}</td>
            <td>${student.lastname}</td>
            <td>
                <a href="/auth/student/${student.id}" class="btn btn-outline-success">生徒詳細画面へ</a>
            </td>
        </tr>
        `;
    }

    return fistElements + columnElements + endElements;
}

function createErrorElement() {
    return `
    <p>申し訳ありません、エラーが発生しました。もう一度お試しください。</p>
    `
}

function createEmptyElement() {
    return `
    <p>まだ生徒を登録していません。</p>
    `
}

async function fetchTeamMember(teamId) {
    const token = document.querySelector('meta[name="_csrf"]').content; 
    const tokenType = document.querySelector('meta[name="_csrf_header"]').content;

    const targetElement = document.getElementById(`student-list-${teamId}`);

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
        
        targetElement.innerHTML = data.length ? createStudentElements(data) : createEmptyElement();
    } catch (e) {
        targetElement.innerHTML = createErrorElement();
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
