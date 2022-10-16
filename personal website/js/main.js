
highlight_currentTab();

showMenu();

function highlight_currentTab(){
  const current_li  = document.querySelectorAll(".currentTab");
  const current_section = document.querySelectorAll("section");

  function activeTab(){
  let section_length = current_section.length;

    while(--section_length && window.scrollY - 97 <
      current_section[section_length].offsetTop){}

    current_li.forEach(ltx =>ltx.classList.remove("active"));
    current_li[section_length].classList.add("active");
  }
  activeTab();
  window.addEventListener("scroll", activeTab);
}

function showMenu(){
  const menu = document.querySelector('header');

  window.addEventListener('scroll', ()=>{
    if(window.scrollY >= 20){
      menu.classList.add('activeHeader');
    }else {
      menu.classList.remove('activeHeader');
    }
  })
}
