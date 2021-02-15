console.clear();
/* Top-bar 스크롤 애니메이션 시작 */
// Hide Header on on scroll down
var didScroll;
var lastScrollTop = 0;
var delta = 5;
var navbarHeight = $('.head-nav').outerHeight();

$(window).scroll(function(event){
    didScroll = true;
});

setInterval(function() {
    if (didScroll) {
        hasScrolled();
        didScroll = false;
    }
}, 250);

function hasScrolled() {
    var st = $(this).scrollTop();
    
    // Make sure they scroll more than delta
    if(Math.abs(lastScrollTop - st) <= delta)
        return;
    
    // If they scrolled down and are past the navbar, add class .nav-up.
    // This is necessary so you never see what is "behind" the navbar.
    if (st > lastScrollTop && st > navbarHeight){
        // Scroll Down
        $('.head-nav').removeClass('nav-down').addClass('nav-up');
      $('.title').addClass('title-down');
      $('.mobile-article-list__cell-search').addClass('search-bar-up');
    } else {
        // Scroll Up
        if(st + $(window).height() < $(document).height()) {
            $('.head-nav').removeClass('nav-up').addClass('nav-down');
          $('.title').removeClass('title-down');
          $('.mobile-article-list__cell-search').removeClass('search-bar-up');
        }
    }
    
    lastScrollTop = st;
}

/* Top-bar 스크롤 애니메이션 끝 */




/* MobileTopBar 옵션 시작 */
function MobileTopBar__init() {
  $('.mobile-top-bar_btn-toggle-side-bar').click(function() {
    let $this = $(this);
    
    if ( $this.hasClass('active') ) {
      $this.removeClass('active');
      $('.mobile-side-bar').removeClass('active');
    }
    else {
      $this.addClass('active');
      $('.mobile-side-bar').addClass('active');
    }
  });
}

MobileTopBar__init();
/* MobileTopBar 옵션 끝 */

/* mobile-side-bar_menu 옵션 시작 */

    $(document).ready(function(){

        $(".mobile-side-bar_menu>ul>li>a").click(function(){
            var submenu = $(this).next(".hide");
 

            if( submenu.is(":visible") ){
                submenu.slideUp();
            }else{
                submenu.slideDown();
            }
        });
    });


//출처: https://stove99.tistory.com/103 [스토브 훌로구]
/* mobile-side-bar_menu 옵션 끝 */


/* top & bottom-button 옵션 시작 */
$(function() {  
  var scrollBottom = $(document).height() - $(window).height() - $(window).scrollTop();
  // 보이기 | 숨기기
  $(window).scroll(function() { 
    if ($(this).scrollTop() < 200) { 
    //200 넘으면 버튼이 보임 
     $('.top-button').fadeOut(),
       $('.bottom-button').fadeOut();  
  } else { 
    $('.top-button').fadeIn(),
      $('.bottom-button').fadeIn(); 
  } 
  }); 
  // 버튼 클릭시 0 까지 animation 이동합니다. 
  $(".top-button").click(function() {
    $('html, body').animate({ 
      scrollTop : 0 }, 200); // 속도 200 
    return false; 
  }),
    $(".bottom-button").click(function() {
    $('html, body').animate({ 
      scrollTop : scrollBottom }, 200); // 속도 200 
    return false; 
  }); 

});

/* top & bottom-button 옵션 끝 */





/* TOAST-UI 시작 */


function ArticleDetail__Body__init() {
	if (toastui === undefined) {
		return;
	}

/* 유튜브 함수 시작 */

	//유튜브 영상임을 감지하고 공간을 형성하는 함수
	function youtubePlugin() {
		toastui.Editor.codeBlockManager.setReplacer('youtube', youtubeId => {
			// Indentify multiple code blocks
			const wrapperId = `yt${Math.random()
				.toString(36)
				.substr(2, 10)}`;

			// Avoid sanitizing iframe tag
			setTimeout(renderYoutube.bind(null, wrapperId, youtubeId), 0);

			return `<div id="${wrapperId}"></div>`;
		});
	}
	//유튜브 영상 렌더링 함수
	function renderYoutube(wrapperId, youtubeId) {
		const el = document.querySelector(`#${wrapperId}`);

		el.innerHTML = `<div class="toast-ui-youtube-plugin-wrap"><iframe src="https://www.youtube.com/embed/${youtubeId}" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></div>`;
	}
	/* 유튜브 함수 끝 */
	
	
	
	/* codepen 함수 시작 */
function codepenPlugin() {
  toastui.Editor.codeBlockManager.setReplacer('codepen', url => {
    const wrapperId = `yt${Math.random().toString(36).substr(2, 10)}`;

    // Avoid sanitizing iframe tag
    setTimeout(renderCodepen.bind(null, wrapperId, url), 0);

    return `<div id="${wrapperId}"></div>`;
  });
}

function renderCodepen(wrapperId, url) {
  const el = document.querySelector(`#${wrapperId}`);
  
  var urlParams = new URLSearchParams(url.split('?')[1]);
  var height = urlParams.get('height');

  el.innerHTML = `<div class="toast-ui-codepen-plugin-wrap"><iframe height="${height}" scrolling="no" src="${url}" frameborder="no" loading="lazy" allowtransparency="true" allowfullscreen="true"></iframe></div>`;
}
/* codepen 함수 끝 */
	
	
/* toastui-editor 함수 시작 */
function Editor__init() {
  $('.toast-ui-editor').each(function(index, node) {
    var initialValue = $(node).prev().html().trim().replace(/t-script/gi, 'script');

    var editor = new toastui.Editor({
      el: node,
      previewStyle: 'vertical',
      initialValue: initialValue,
      height:600,
      plugins: [toastui.Editor.plugin.codeSyntaxHighlight, youtubePlugin, codepenPlugin]
    });

	$(node).data('data-toast-editor', editor);
  });
}

/* toastui-editor 함수 끝 */


/* toastui-viewer 함수 시작 */
function EditorViewer__init() {
	  $('.toast-ui-viewer').each(function(index, node) {
	    var initialValue = $(node).prev().html().trim().replace(/t-script/gi, 'script');

//	var body = document.querySelector('.article-detail-cell__body > div > span');
//	var initValue = body.innerHTML.trim();

	
	var viewer = new toastui.Editor.factory({
		el: node,
		initialValue: initialValue,
		viewer: true,
		plugins: [toastui.Editor.plugin.codeSyntaxHighlight, youtubePlugin, codepenPlugin]
	});
});
}
	EditorViewer__init();
	Editor__init();
}

ArticleDetail__Body__init();

/* toastui-viewer 함수 끝 */

/* TOAST-UI 끝 */


/* 댓글 수정 버튼 시작 */
function doModifyReplyForm__init() {
  $('.doModifyReplyForm').click(function() {

      $('.doModifyReplyForm').addClass('active');
      $(this).parents().parents().prev('.reply-list-box__cell-contents').addClass('active');
      $(this).parents().parents().addClass('active');
      $(this).parents().addClass('active');
      $(this).parents().prev().addClass('active');
      $(this).next().addClass('active');

  });
}

doModifyReplyForm__init();
/* 댓글 수정 버튼 끝 */