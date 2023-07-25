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
const CONFIRM_ID_PHRASE = 'confirm-box'; // 確認画面で用いるidの固定値
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


/**
 * confirmIdに対応したポップアップを削除
 * 
 * @param confirmId 認証用のポップアップを作成したときに生成したid
 */
async function removeConfirmBox (confirmId) {
    if (confirmId !== CONFIRM_TARGET_INFO.id) throw new Error('Invalid id of confirm.');
  
    CONFIRM_TARGET_INFO = {};
  
    document.getElementById(CONFIRM_ID_PHRASE).classList.remove('is-active');
  
    await new Promise(resolve => setTimeout(resolve, 500));
  
    document.getElementById(CONFIRM_ID_PHRASE).remove();
}

/**
 * confirmIdに対応したポップアップの確定ボタンが押させた時に実行する関数。
 * confirmIdに対応するconfirmNameのformを確定し、ポップアップを削除する
 * 
 * @param confirmId 認証用のポップアップを作成した時に生成したid
 */  
async function admitConfirm (confirmId) {
    if (confirmId !== CONFIRM_TARGET_INFO.id) throw new Error('Invalid id of confirm.');
    
    document.forms[CONFIRM_TARGET_INFO.formName].submit();
  
    await removeConfirmBox(confirmId);
}

/**
 * ポップアップを生成
 * 
 * @param formName 確定ボタンが押された時に実行したいフォームの名前
 * @param message ポップアップに表示するメッセージ
 */
async function createConfirmBox(formName, message = '') {
    // 既に表示されていたら
    if (document.getElementById(CONFIRM_ID_PHRASE) !== null) {
        if (typeof CONFIRM_TARGET_INFO.id === 'undefined') document.getElementById(CONFIRM_ID_PHRASE).remove();
        else await removeConfirmBox(CONFIRM_TARGET_INFO.id);
    }
    const div = document.createElement('div');
    div.classList.add('confirm-box');
    div.setAttribute('id', CONFIRM_ID_PHRASE);

    const id = Math.random().toString(32).substring(2); // idの生成

    CONFIRM_TARGET_INFO.id = id;
    CONFIRM_TARGET_INFO.formName = formName;

    /* ポップアップの生成 */
    div.innerHTML = `
      <div class="confirm-content-box">
        <h3>確認</h3>
        <p>${message}</p>
        <div class="confirm-button-box">
          <button onclick="removeConfirmBox('${id}')" class="btn btn-outline-warning">
            戻る
            <i class="bi bi-arrow-clockwise"></i>
          </button>
          <button onclick="admitConfirm('${id}')" class="btn btn-outline-danger">
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

