console.clear();
/* Top-bar 스크롤 애니메이션 시작 */
// Hide Header on on scroll down
var didScroll;
var lastScrollTop = 0;
var delta = 5;
var navbarHeight = $(".head-nav").outerHeight();

$(window).scroll(function (event) {
  didScroll = true;
});

setInterval(function () {
  if (didScroll) {
    hasScrolled();
    didScroll = false;
  }
}, 200);

function hasScrolled() {
  var st = $(this).scrollTop();

  // Make sure they scroll more than delta
  if (Math.abs(lastScrollTop - st) <= delta) return;

  // If they scrolled down and are past the navbar, add class .nav-up.
  // This is necessary so you never see what is "behind" the navbar.
  if (st > lastScrollTop && st > navbarHeight) {
    // Scroll Down
    $(".head-nav").removeClass("nav-down").addClass("nav-up");
    $(".title").addClass("title-down");
    $(".mobile-article-list__cell-search").addClass("search-bar-up");
  } else {
    // Scroll Up
    if (st + $(window).height() < $(document).height()) {
      $(".head-nav").removeClass("nav-up").addClass("nav-down");
      $(".title").removeClass("title-down");
      $(".mobile-article-list__cell-search").removeClass("search-bar-up");
    }
  }

  lastScrollTop = st;
}

/* Top-bar 스크롤 애니메이션 끝 */



/* MobileTopBar 옵션 시작 */
function MobileTopBar__init() {
  $(".mobile-top-bar_btn-toggle-side-bar").click(function () {
    let $this = $(this);

    if ($this.hasClass("active")) {
      $this.removeClass("active");
      $(".mobile-side-bar").removeClass("active");
      $(".mobile-side-bar").off("scroll touchmove mousewheel");
    } else {
      $this.addClass("active");
      $(".mobile-side-bar").addClass("active");
      $(".mobile-side-bar").on("scroll touchmove mousewheel", function (e) {
        e.preventDefault();
        e.stopPropagation();
        return false;
      });
    }
  });
}

MobileTopBar__init();
/* MobileTopBar 옵션 끝 */



/* mobile-side-bar_menu 옵션 시작 */

$(document).ready(function () {
  $(".mobile-side-bar_menu>ul>li>a").click(function () {
    var submenu = $(this).next(".hide");

    if (submenu.is(":visible")) {
      submenu.slideUp();
    } else {
      submenu.slideDown();
    }
  });
});

//출처: https://stove99.tistory.com/103 [스토브 훌로구]
/* mobile-side-bar_menu 옵션 끝 */



/* top & bottom-button 옵션 시작 */
$(function () {
  var scrollBottom =
    $(document).height() - $(window).height() - $(window).scrollTop();
  // 보이기 | 숨기기
  $(window).scroll(function () {
    if ($(this).scrollTop() < 200) {
      //200 넘으면 버튼이 보임
      $(".top-button").fadeOut(), $(".bottom-button").fadeOut();
    } else {
      $(".top-button").fadeIn(), $(".bottom-button").fadeIn();
    }
  });
  // 버튼 클릭시 0 까지 animation 이동합니다.
  $(".top-button").click(function () {
    $("html, body").animate(
      {
        scrollTop: 0
      },
      200
    ); // 속도 200
    return false;
  }),
    $(".bottom-button").click(function () {
      $("html, body").animate(
        {
          scrollTop: scrollBottom
        },
        200
      ); // 속도 200
      return false;
    });
});

/* top & bottom-button 옵션 끝 */



/* 홈 스크롤 애니메이션 시작 */
var Pages__nowWorking = false;

function Pages__goToScroll(top) {
  if (Pages__nowWorking) {
    return false;
  }

  Pages__nowWorking = true;

  $("html,body")
    .stop()
    .animate(
      {
        scrollTop: top - 40
      },
      500,
      function () {
        Pages__nowWorking = false;
      }
    );
}

function Pages__goTo(index) {
  var $page = $(".section-home > .section-home-box");

  if (index < 0) {
    index = 0;
  } else if (index >= $page.length) {
    index = $page.length - 1;
  }

  var top = parseInt(
    $(".section-home > .section-home-box").eq(index).attr("data-offsetTop")
  );

  Pages__goToScroll(top);
}

var Pages__activedMenuItem = -1;

function Pages__activeMenuItem(index) {
  if (Pages__activedMenuItem == index) {
    return false;
  }
  Pages__activedMenuItem = index;
}

function Pages__init() {
  $(".section-home > .section-home-box").on(
    "mousewheel DOMMouseScroll",
    function (e) {
      var index = $(this).index();

      // html, body 에 마우스 휠 이벤트와 돔마우스스크롤 이벤트를 걸었습니다.
      var E = e.originalEvent;
      // 변수 E 에다가는 이벤트 객체의 속성으로 사용할 수 있는 속성 인 originalEvent 를 넣었습니다.
      delta = 0;
      if (E.detail) {
        delta = E.detail * -40;
      } else {
        delta = E.wheelDelta;
      }

      if (delta > 0) {
        Pages__goTo(index - 1);
      } else {
        Pages__goTo(index + 1);
      }

      return false;
    }
  );

  $(window).scroll(function () {
    var scrollTop = $(this).scrollTop();
    $(".section-home > .section-home-box").each(function (index, node) {
      var offsetTop = parseInt($(this).attr("data-offsetTop"));

      if (scrollTop <= offsetTop) {
        Pages__activeMenuItem(index);
        return false;
      }
    });
  });

  $(window)
    .resize(function () {
      Pages__updatePageShapeInfo();

      $(window).scroll();
    })
    .resize();
}

function Pages__updatePageShapeInfo() {
  $(".section-home > .section-home-box").each(function (index, node) {
    var width = $(this).width();
    var height = $(this).height();
    var offsetTop = $(this).offset().top;

    $(this).attr("data-width", width);
    $(this).attr("data-height", height);
    $(this).attr("data-offsetTop", offsetTop);
  });
}

Pages__init();
/* 홈 스크롤 애니메이션 끝 */



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
      previewStyle: 'tab',
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
      $('.reply-detail-cell-likesCount').addClass('active');
      $('.mobile-reply-list-box__cell-body').addClass('active');
      $(this).parents().parents().prev('.reply-list-box__cell-contents').addClass('active');
      $(this).parents().parents().addClass('active');
      $(this).parents().addClass('active');
      $(this).parents().prev().addClass('active');
      $('.reply-list-box-cell__option-btns').next().addClass('active');

  });
}

doModifyReplyForm__init();
/* 댓글 수정 버튼 끝 */
/* 대댓글 등록 버튼 시작 */
function doRereplyForm__init() {
  $('.reply-list-box__cell-reReply').click(function() {
    const target = $(this).parents().parents().next();
    
    if(target.hasClass('active')){
      target.removeClass('active');
    }
    else{
      target.addClass('active');
    }
      
  });
}

doRereplyForm__init();
/* 대댓글 등록 버튼 끝 */
/* 대댓글 수정 버튼 시작 */
function doModifyreReplyForm__init() {
  $('.doModifyreReplyForm').click(function() {

      $('.doModifyreReplyForm').addClass('active');
      $('.reReply-detail-cell-likesCount').addClass('active');
      $('.mobile-reReply-list-box__cell-body').addClass('active');
      $(this).parents().parents().prev('.reReply-list-box__cell-contents').addClass('active');
      $(this).parents().parents().addClass('active');
      $(this).parents().addClass('active');
      $(this).parents().prev().addClass('active');
      $('.reReply-list-box-cell__option-btns').next().addClass('active');

  });
}

doModifyreReplyForm__init();
/* 대댓글 수정 버튼 끝 */