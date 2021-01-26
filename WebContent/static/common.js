console.clear();
/* 모바일용 메뉴 버튼 시작 */
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
/* 모바일용 메뉴 버튼 끝 */


/* 홈 슬라이드 시작 */
//current position
var pos = 0;
//number of slides
var totalSlides = $('#slider-wrap ul li').length;
//get the slide width
var sliderWidth = $('#slider-wrap').width();


$(document).ready(function(){
  
  
  /*****************
   BUILD THE SLIDER
  *****************/
  //set width to be 'x' times the number of slides
  $('#slider-wrap ul#slider').width(sliderWidth*totalSlides);
  
    //next slide  
  $('#next').click(function(){
    slideRight();
  });
  
  //previous slide
  $('#previous').click(function(){
    slideLeft();
  });
  
  
  
  /*************************
   //*> OPTIONAL SETTINGS
  ************************/
  //automatic slider
  var autoSlider = setInterval(slideRight, 5000);
  
  //for each slide 
  $.each($('#slider-wrap ul li'), function() { 

     //create a pagination
     var li = document.createElement('li');
     $('#pagination-wrap ul').append(li);    
  });
  
  //counter
  countSlides();
  
  //pagination
  pagination();
  
  //hide/show controls/btns when hover
  //pause automatic slide when hover
  $('#slider-wrap').hover(
    function(){ $(this).addClass('active'); clearInterval(autoSlider); }, 
    function(){ $(this).removeClass('active'); autoSlider = setInterval(slideRight, 3000); }
  );
  
  

});//DOCUMENT READY
  


/***********
 SLIDE LEFT
************/
function slideLeft(){
  pos--;
  if(pos==-1){ pos = totalSlides-1; }
  $('#slider-wrap ul#slider').css('left', -(sliderWidth*pos));  
  
  //*> optional
  countSlides();
  pagination();
}


/************
 SLIDE RIGHT
*************/
function slideRight(){
  pos++;
  if(pos==totalSlides){ pos = 0; }
  $('#slider-wrap ul#slider').css('left', -(sliderWidth*pos)); 
  
  //*> optional 
  countSlides();
  pagination();
}

  
/************************
 //*> OPTIONAL SETTINGS
************************/
function countSlides(){
  $('#counter').html(pos+1 + ' / ' + totalSlides);
}

function pagination(){
  $('#pagination-wrap ul li').removeClass('active');
  $('#pagination-wrap ul li:eq('+pos+')').addClass('active');
}

/*출처
https://kutar37.tistory.com/entry/%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C%EC%87%BC-%EA%B5%AC%ED%98%84-cssjavascriptjquery
*/
/* 홈 슬라이드 끝 */






/* 회원정보 수정 버튼 시작 */
function memberModifyInfo__init() {
  $('.infoModifyBtn0').click(function() {

      $('.infoModifyBtn0').addClass('active');
      $('.oldLoginIdInfo').addClass('active');
      $('.newLoginIdInfo').addClass('active');  
  }),
   $('.infoModifyBtn1').click(function() {

      $('.infoModifyBtn1').addClass('active');
      $('.oldNameInfo').addClass('active');
      $('.newNameInfo').addClass('active'); 
  }),
   $('.infoModifyBtn2').click(function() {

      $('.infoModifyBtn2').addClass('active');
      $('.oldNicknameInfo').addClass('active');
      $('.newNicknameInfo').addClass('active');
    
  }),
   $('.infoModifyBtn3').click(function() {
    
      $('.infoModifyBtn3').addClass('active');
      $('.oldEmailInfo').addClass('active');
      $('.newEmailInfo').addClass('active');
    
  }),
   $('.infoModifyBtn4').click(function() {
    
      $('.infoModifyBtn4').addClass('active');
      $('.oldPhoneInfo').addClass('active');
      $('.newPhoneInfo').addClass('active');
    
  });
}

memberModifyInfo__init();

function memberModifyInfo__remove() {
  $('.cleModifyBtn0').click(function() {

      $('.infoModifyBtn0').removeClass('active');
      $('.oldLoginIdInfo').removeClass('active');
      $('.newLoginIdInfo').removeClass('active');  
  }),
   $('.cleModifyBtn1').click(function() {

      $('.infoModifyBtn1').removeClass('active');
      $('.oldNameInfo').removeClass('active');
      $('.newNameInfo').removeClass('active'); 
  }),
   $('.cleModifyBtn2').click(function() {

      $('.infoModifyBtn2').removeClass('active');
      $('.oldNicknameInfo').removeClass('active');
      $('.newNicknameInfo').removeClass('active');
    
  }),
   $('.cleModifyBtn3').click(function() {
    
      $('.infoModifyBtn3').removeClass('active');
      $('.oldEmailInfo').removeClass('active');
      $('.newEmailInfo').removeClass('active');
    
  }),
   $('.cleModifyBtn4').click(function() {
    
      $('.infoModifyBtn4').removeClass('active');
      $('.oldPhoneInfo').removeClass('active');
      $('.newPhoneInfo').removeClass('active');
    
  });
};

memberModifyInfo__remove();

/* 회원정보 수정 버튼 끝 */



/* top-button 옵션 시작 */
$(function() {  
  // 보이기 | 숨기기
  $(window).scroll(function() { 
    if ($(this).scrollTop() < 200) { 
    //200 넘으면 버튼이 보임 
     $('.top-button').fadeOut(); 
  } else { 
    $('.top-button').fadeIn();
  } 
  }); 
  // 버튼 클릭시 0 까지 animation 이동합니다. 
  $(".top-button").click(function() {
    $('html, body').animate({ 
      scrollTop : 0 }, 200); // 속도 200 
    return false; 
  }); 

});

/* top-button 옵션 끝 */


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

