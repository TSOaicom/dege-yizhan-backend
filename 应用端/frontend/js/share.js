// 创建 style 元素
function createStyleSheet(path, lang) {
  // 创建 style 元素
  const style = document.createElement('style');

  style.innerHTML = `
    #toolBox {
      overflow: hidden;
      box-sizing: border-box;
      position: fixed;
      right: 0;
      bottom: 37px;
      padding: 12px 0 0 4px;
      width: 102px;
      height: 56px;
      z-index:999;
      background: url(${path}/bg_collapsed_${lang}.png) no-repeat 0 0 / 100% auto;
    }
    #toolBox.is-expanded {
      height: 118px;
      background: url(${path}/bg_expanded_${lang}.png) no-repeat 0 0 / 100% auto;
    }

    #toggleButton {
      height: 26px;
      cursor: pointer;
    }

    #buttonsWrap {
      display: none;
      position: absolute;
      top: 50px;
      left: 15px;
    }
    #toolBox.is-expanded #buttonsWrap {
      display: block;
    }

    #buttonsWrap > * {
      display: block;
      position: relative;
      width: 80px;
      height: 25px;
      cursor: pointer;
    }
    #buttonsWrap > * + * {
      margin-top: 9px;
    }
    #buttonsWrap > *:before {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      opacity: 0;
      transition: opacity 0.1s ease-in-out;
      content: "";
    }
    #buttonsWrap > *:hover::before {
      opacity: 1;
    }

    #likeButton {
      position: relative;
      background: url(${path}/like_btn_normal_${lang}.png) no-repeat 0 0 / 100% 100%;
    }
    #likeButton:before {
      background: url(${path}/like_btn_hover_${lang}.png) no-repeat 0 0 / 100% 100%;
    }
    #likeButton .like-count {
      overflow: hidden;
      position: absolute;
      top: 50%;
      left: 6px;
      margin-top: -16px;
      width: 30px;
      height: 30px;
      font-size: 12px;
      line-height: 30px;
      text-align: center;
      color: #fff;
      background: url(${path}/heart_normal.png) no-repeat 0 0 / 100% 100%;
      transform: scale(0.5);
      transform-origin: 0 center;
    }
    #likeButton.is-like .like-count {
      background-image: url(${path}/heart_liked.png);
    }
    
    #buildButton {
      background: url(${path}/build_btn_normal_${lang}.png) no-repeat 0 0 / 100% 100%;
    }
    #buildButton:before {
      background: url(${path}/build_btn_hover_${lang}.png) no-repeat 0 0 / 100% 100%;
    }

    #closeButton {
      position: absolute;
      top: 2px;
      right: 5px;
      border-radius: 50%;
      width: 14px;
      height: 14px;
      background: #EEEEEE url(${path}/close.png) no-repeat 0 0 / 100% 100%;
      transition: background-color 0.1s ease-in-out;
      cursor: pointer;
    }
    #closeButton:hover {
      background-color: #E3E3E3;
    }
  `;

  // 添加到head
  document.head.appendChild(style);
}

window.onload = function () {
  // 获取当前URL的查询参数
  const urlParams = new URLSearchParams(window.location.search);

  // 获取当前语言（默认英文）
  const languages = { 'en-US': 'en', 'zh-CN': 'cn' };
  const lang = urlParams.get('lang') ? languages[urlParams.get('lang')] : 'en';

  console.log(lang);

  // 获取点赞状态
  const liked = urlParams.get('liked') === 'true' ? true : false;


  console.log(liked);

  // 获取点赞数
  const listCount = urlParams.get('likeCount') ? parseInt(urlParams.get('likeCount')) : 0;

  const isShow = urlParams.get('show') === 'false' ? false : true;

  if (!isShow)
    return false;

  // 网站首页链接
  const link = 'https://dev.codeflying.app';

  // 图片地址
  const path = 'https://kuafuai.obs.cn-east-3.myhuaweicloud.com:443/h_w_image'

  // 创建 style 元素
  createStyleSheet(path, lang);

  // 创建元素 toolBox
  const toolBox = document.createElement('div');

  toolBox.id = 'toolBox';

  // 创建元素 toggleButton
  const toggleButton = document.createElement('div');

  toggleButton.id = 'toggleButton';
  toolBox.appendChild(toggleButton);

  toggleButton.onclick = function () {
    // 不展开直接跳转
    window.location.href = link;


    // if (toolBox.classList.contains('is-expanded')) {
    //     toolBox.classList.remove('is-expanded')
    // } else {
    //     toolBox.classList.add('is-expanded')
    // }
  }

  // 创建元素 buttonsWrap
  const buttonsWrap = document.createElement('div');

  buttonsWrap.id = 'buttonsWrap';
  toolBox.appendChild(buttonsWrap);

  // 创建元素 点赞按钮
  const likeButton = document.createElement('div');

  likeButton.id = 'likeButton';
  likeButton.innerHTML = `<div class="like-count">${listCount}</like>`;
  if (liked) likeButton.classList.add('is-like');
  buttonsWrap.appendChild(likeButton);

  // 点赞事件
  likeButton.onclick = function () {
    if (liked) return;
    likeButton.classList.add('is-like');
    likeButton.querySelector('.like-count').innerHTML = listCount + 1;
  }

  // 创建元素 打造我的应用按钮
  const buildButton = document.createElement('a');

  buildButton.id = 'buildButton';
  buildButton.setAttribute('href', link);
  buttonsWrap.appendChild(buildButton);

  // 创建关闭按钮
  const closeButton = document.createElement('div');

  closeButton.id = 'closeButton';
  toolBox.appendChild(closeButton);

  closeButton.onclick = function () {
    document.getElementById('toolBox').style.display = 'none';
  }

  // 添加到 body 末尾
  document.body.appendChild(toolBox);
}