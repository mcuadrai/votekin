/*$(document).ready(function(){

  wow = new WOW(
    {
      animateClass: 'animated',
      offset: 100,
      callback: function (box) {
        console.log("WOW: animating <" + box.tagName.toLowerCase() + ">")
      }
    }
  );
  wow.init();



});*/


// Negative

$('#NegativeId2').hide()
$('#NegativeId1').show()

function showneg(val){
  if(val == 0){
    $('#NegativeId1').hide()
    $('#NegativeId2').show()
  }
  else{
    $('#NegativeId2').hide()
    $('#NegativeId1').show()
  }
}
// Interesting

$('#InterestingId2').hide()
$('#InterestingId1').show()

function showint(val){
  if(val == 0){
    $('#InterestingId1').hide()
    $('#InterestingId2').show()
  }
  else{
    $('#InterestingId2').hide()
    $('#InterestingId1').show()
  }
}

// ViolentId1

$('#ViolentId2').hide()
$('#ViolentId1').show()

function showvio(val){
  if(val == 0){
    $('#ViolentId1').hide()
    $('#ViolentId2').show()
  }
  else{
    $('#ViolentId2').hide()
    $('#ViolentId1').show()
  }
}


/*function mypost() {
  $(".active-input").addClass("active");
  var x = document.getElementById("post-project");
  if (x.style.display === "inline-block") {
    x.style.display = "none";
    $(".active-input").removeClass("active");
  } else {
    x.style.display = "inline-block";
    $(".active-input").addClass("active");
  }
}

function postdetail() {
  var x = document.getElementById("dropdown-container");
  if (x.style.display === "inline-block") {
    x.style.display = "none";
  } else {
    $('#dropdown-tag').hide();
    $('#dropdown-location').hide();
    x.style.display = "inline-block";
  }
  e.stopPropagation();
}*/

function posttag() {
  var x = document.getElementById("dropdown-tag");
  if (x.style.display === "inline-block") {
    x.style.display = "none";
  } else {
    $('#dropdown-container').hide();
    $('#dropdown-location').hide();
    x.style.display = "inline-block";
  }
}

function postlocation() {
  var x = document.getElementById("dropdown-location");
  if (x.style.display === "inline-block") {
    //x.style.display = "none";
  } else {
    $('#dropdown-tag').hide();
    $('#dropdown-container').hide();
    x.style.display = "inline-block";
  }
}

function mypostpop() {
  $("input").addClass("active");
  var x = document.getElementById("post-project-pop");
  if (x.style.display === "inline-block") {
    x.style.display = "none";
    $("input").removeClass("active");
  } else {
    x.style.display = "inline-block";
    $("input").addClass("active");
  }
}

$('#sidemenu-btnmenutoggle').click(function () {
    $('body').toggleClass('sideMhide')
});
