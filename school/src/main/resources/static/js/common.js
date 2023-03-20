/*******
 * 
 * 全てのファイルに適用されるjsファイル
 * 
 * ⚠️ 必ず関数をコメントアウトして、その関数の目的を明示すること！ ⚠️
 * 
 * - グローバル変数は極力使用しないこと
 * - 使用する場合は、common.jsの先頭部に宣言し、使用目的等を明示すること
 * - グローバル変数を使用する際には、全て大文字のスネークケースで宣言すること
 * 
 *******/


/* confirm関連 */
const CONFIRM_ID_PHRASE = 'tree-confirm-box'; // 確認画面で用いるidの固定値
let CONFIRM_TARGET_INFO = {
  id: '',
  formName: ''
};

/**
 * bootstrapより追加されたダークモードの切り替えを行う
 */
function changeDarkMode() {
    const START_DARK_HOUR = 18;
    const END_DARK_HOUR = 5;

    const hour = new Date().getHours();

    const htmlEle = document.getElementsByTagName('html');
    
    htmlEle[0].setAttribute('data-bs-theme', START_DARK_HOUR <= hour || hour <= END_DARK_HOUR ? 'dark' : 'light');
}

async function removeConfirmBox (confirmId) {
    if (confirmId !== CONFIRM_TARGET_INFO.id) throw new Error('Invalid id of confirm.');
  
    CONFIRM_TARGET_INFO = {};
  
    document.getElementById(CONFIRM_ID_PHRASE).classList.remove('is-active');
  
    await new Promise(resolve => setTimeout(resolve, 500));
  
    document.getElementById(CONFIRM_ID_PHRASE).remove();
}
  
async function admitConfirm (confirmId) {
    if (confirmId !== CONFIRM_TARGET_INFO.id) throw new Error('Invalid id of confirm.');
    
    document[CONFIRM_TARGET_INFO.formName].submit();
  
    await removeConfirmBox(confirmId);
}

async function createConfirmBox(formName, message = '') {
    // 既に表示されていたら
    if (document.getElementById(CONFIRM_ID_PHRASE) !== null) {
      if (typeof CONFIRM_TARGET_INFO.id === 'undefined') document.getElementById(CONFIRM_ID_PHRASE).remove();
      else await removeConfirmBox(CONFIRM_TARGET_INFO.id);
    }
    const div = document.createElement('div');
    div.classList.add('tree-confirm-box');
    div.setAttribute('id', CONFIRM_ID_PHRASE);

    const id = Math.random().toString(32).substring(2);

    CONFIRM_TARGET_INFO.id = id;
    CONFIRM_TARGET_INFO.formName = formName;

    div.innerHTML = `
      <div class="tree-confirm-content-box">
        <h3>確認</h3>
        <p>${message}</p>
        <div class="tree-confirm-button-box">
          <button onclick="removeConfirmBox('${id}')" class="tree-button tree-button-mini">
            戻る
            <i class="bi bi-arrow-clockwise"></i>
          </button>
          <button onclick="admitConfirm('${id}')" class="tree-button tree-button-mini tree-button-danger">
            確認
            <i class="bi bi-check"></i>
          </button>
        </div>
      </div>
    `;

    const bodyEle = document.getElementsByTagName('body')[0];

    bodyEle.appendChild(div);

    await new Promise(resolve => setTimeout(resolve, 100));

    document.getElementById(CONFIRM_ID_PHRASE).classList.add('is-active');
}

function init() {
    changeDarkMode();
}

init();

