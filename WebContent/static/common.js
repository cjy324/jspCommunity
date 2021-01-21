console.clear();
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


/*
//좌우슬라이딩
$(function() {
  var winW = cnt = setId = 0;
  resizeFn();

  function resizeFn() { //반응형 이미지크기 조정 함수
    winW = $(window).innerWidth();
    $(".slide").css({
      width: winW-500
    }); //창크기에 슬라이드 이미지 맞춤
  };

  $(window).resize(function() {
    resizeFn();  //창크기 변경될 때 마다 함수 반복 실행
  });

  autoplayFn();

  function autoplayFn() {  //2.5초마다 슬라이드 자동 작동
    setId = setInterval(nextCountFn, 2500);
  };

  $(".pageBt").each(function(idx) {  //page버튼 클릭시마다 해당 이미지로 이동
    $(this).click(function() {
      clearInterval(setId);  //autoplay함수 정지
      cnt = idx;
      mainslideFn();
    });
  });

  function nextCountFn() {  //count(cnt)가 증가될때마다 슬라이드 작동
    cnt++;
    mainslideFn();
  };

  function prevCountFn() { //count(cnt)가 감소될때마다 슬라이드 작동
    cnt--;
    mainslideFn();
  };

function mainslideFn() {  //메인슬라이드 함수
    $(".slideWrap").stop().animate({
      left: (-100 * cnt) + "%"
    }, 600, function() {
      if (cnt > 3) {
        cnt = 0;  //count가 끝까지 이동했을대 다시 처음으로 돌아감
      }
      if (cnt < 0) {
        cnt = 3
      }
      $(".slideWrap").stop().animate({
        left: (-100 * cnt) + "%"
      }, 0)
    });
    $(".pageBt").removeClass("addPageBt");
    $(".pageBt").eq(cnt > 3 ? cnt = 0 : cnt).addClass("addPageBt");
  };
  //animation 사용시에는 stop을 넣어 부부드럽게
  //count 변경시마다 버튼 색깔 변경
  
});
*/