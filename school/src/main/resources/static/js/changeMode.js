function changeDarkMode() {
    const hour = new Date().getHours();

    const htmlEle = document.getElementsByTagName('html');
    
    htmlEle[0].setAttribute('data-bs-theme', (18 >= hour || hour <= 5) ? 'dark' : 'light');
}

changeDarkMode();
