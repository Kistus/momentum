let burger=document.querySelector('.burger');        
let navbar=document.querySelector('.menu1');
let out=document.querySelector('.main');
let home=document.querySelectorAll('.nav-el1');

let button = document.querySelectorAll('.service-content-button')
let cards = document.querySelectorAll('.service-services-big-item')
let gaden = document.querySelectorAll('.gardens-card')
let lawn = document.querySelectorAll('.lawn-card')
let planting = document.querySelectorAll('.planting-card')
let btngrd = document.getElementById('btn-grd')
let btnlwn = document.getElementById('btn-lwn')
let btnplt = document.getElementById('btn-plt')

let pricesbig = document.querySelector('.prices-rect')
let pricesell = document.querySelectorAll('.prices-ellipse')
let arrow = document.querySelectorAll('.carrow')
let bot = document.querySelectorAll('.prices-ell-bot')

    arrow[0].addEventListener("click", function(){
        if(arrow[1].classList.contains("active") || arrow[2].classList.contains("active")){
            arrow[1].classList.remove('active')
            arrow[1].style.transform = "rotate(0deg)"
            pricesell[1].classList.remove('active')
            pricesbig.classList.remove('active')
            bot[1].classList.remove('active')
            arrow[2].classList.remove('active')
            arrow[2].style.transform = "rotate(0deg)"
            pricesell[2].classList.remove('active')
            bot[2].classList.remove('active')
        }
        if(arrow[0].classList.contains("active")){
            arrow[0].classList.remove('active')
            arrow[0].style.transform = "rotate(0deg)"
            pricesell[0].classList.remove('active')
            pricesbig.classList.remove('active')
            bot[0].classList.remove('active')
            return 0;
        }
        arrow[0].classList.toggle('active')
        arrow[0].style.transform = "rotate(180deg)"
        pricesbig.classList.toggle('active')
        pricesell[0].classList.toggle('active')
        bot[0].classList.toggle('active')
    })

    arrow[1].addEventListener("click", function(){
        if(arrow[0].classList.contains("active") || arrow[2].classList.contains("active")){
            arrow[0].classList.remove('active')
            arrow[0].style.transform = "rotate(0deg)"
            pricesell[0].classList.remove('active')
            pricesbig.classList.remove('active')
            bot[0].classList.remove('active')
            arrow[2].classList.remove('active')
            arrow[2].style.transform = "rotate(0deg)"
            pricesell[2].classList.remove('active')
            bot[2].classList.remove('active')
        }
        if(arrow[1].classList.contains("active")){
            arrow[1].classList.remove('active')
            arrow[1].style.transform = "rotate(0deg)"
            pricesell[1].classList.remove('active')
            pricesbig.classList.remove('active')
            bot[1].classList.remove('active')
            return 0;
        }
        arrow[1].classList.toggle('active')
        arrow[1].style.transform = "rotate(180deg)"
        pricesbig.classList.toggle('active')
        pricesell[1].classList.toggle('active')
        bot[1].classList.toggle('active')
    })

    arrow[2].addEventListener("click", function(){
        if(arrow[0].classList.contains("active") || arrow[1].classList.contains("active")){
            arrow[0].classList.remove('active')
            arrow[0].style.transform = "rotate(0deg)"
            pricesell[0].classList.remove('active')
            pricesbig.classList.remove('active')
            bot[0].classList.remove('active')
            arrow[1].classList.remove('active')
            arrow[1].style.transform = "rotate(0deg)"
            pricesell[1].classList.remove('active')
            bot[1].classList.remove('active')
        }
        if(arrow[2].classList.contains("active")){
            arrow[2].classList.remove('active')
            arrow[2].style.transform = "rotate(0deg)"
            pricesell[2].classList.remove('active')
            pricesbig.classList.remove('active')
            bot[2].classList.remove('active')
            return 0;
        }
        arrow[2].classList.toggle('active')
        arrow[2].style.transform = "rotate(180deg)"
        pricesbig.classList.toggle('active')
        pricesell[2].classList.toggle('active')
        bot[2].classList.toggle('active')
    })


    btngrd.addEventListener("click", function () {
        if(btngrd.classList.contains("active")){
            for(let i = 0; i < gaden.length; i++)
                gaden[i].classList.toggle('blur')
            btngrd.classList.remove('active')
            btngrd.style.color = ""
            btngrd.style.background = ""
            return 0;
        }
        if (btnlwn.classList.contains("active") && btnplt.classList.contains("active")) {
            btnlwn.classList.remove("active")
            btnplt.classList.remove("active")
            btngrd.classList.add('active')
            for(let i = 0; i < lawn.length; i++)
                lawn[i].classList.toggle('blur')
            for(let i = 0; i < planting.length; i++)
                planting[i].classList.toggle('blur')
            for(let i = 0; i < gaden.length; i++)
                gaden[i].classList.remove('blur')
            btngrd.style.color = "#fff"
            btngrd.style.background = "#e06733"
            btnlwn.style.color = ""
            btnlwn.style.background = ""
            btnplt.style.color = ""
            btnplt.style.background = ""
        }
        else{
            btngrd.classList.add('active')
            for(let i = 0; i < gaden.length; i++)
                gaden[i].classList.remove('blur')
            btngrd.style.color = "#fff"
            btngrd.style.background = "#e06733"
            for(let i = 0; i < lawn.length; i++){
                if(btnlwn.classList.contains("active"))
                    lawn[i].classList.remove('blur')
                else
                    lawn[i].classList.add('blur')
            }
            for(let i = 0; i < planting.length; i++){
                if(btnplt.classList.contains("active"))
                    planting[i].classList.remove('blur')
                else
                    planting[i].classList.add('blur')
            }
        }
    })

    btnlwn.addEventListener("click", function () {
        if(btnlwn.classList.contains("active")){
            for(let i = 0; i < lawn.length; i++)
                lawn[i].classList.toggle('blur')
            btnlwn.classList.remove('active')
            btnlwn.style.color = ""
            btnlwn.style.background = ""
            return 0;
        }
        if (btngrd.classList.contains("active") && btnplt.classList.contains("active")) {
            btngrd.classList.remove("active")
            btnplt.classList.remove("active")
            btnlwn.classList.add('active')
            for(let i = 0; i < lawn.length; i++)
                lawn[i].classList.remove('blur')
            for(let i = 0; i < planting.length; i++)
                planting[i].classList.toggle('blur')
            for(let i = 0; i < gaden.length; i++)
                gaden[i].classList.toggle('blur')
            btnlwn.style.color = "#fff"
            btnlwn.style.background = "#e06733"
            btngrd.style.color = ""
            btngrd.style.background = ""
            btnplt.style.color = ""
            btnplt.style.background = ""
        }
        else{
            btnlwn.classList.add('active')
            for(let i = 0; i < lawn.length; i++)
                lawn[i].classList.remove('blur')
            btnlwn.style.color = "#fff"
            btnlwn.style.background = "#e06733"
            for(let i = 0; i < planting.length; i++){
                if(btnplt.classList.contains("active"))
                    planting[i].classList.remove('blur')
                else
                    planting[i].classList.add('blur')
            }
            for(let i = 0; i < gaden.length; i++){
                if(btngrd.classList.contains("active"))
                    gaden[i].classList.remove('blur')
                else
                    gaden[i].classList.add('blur')
            }
        }
    })

    btnplt.addEventListener("click", function () {
        if(btnplt.classList.contains("active")){
            for(let i = 0; i < planting.length; i++)
                planting[i].classList.toggle('blur')
            btnplt.classList.remove('active')
            btnplt.style.color = ""
            btnplt.style.background = ""
            return 0;
        }
        if (btngrd.classList.contains("active") && btnlwn.classList.contains("active")) {
            btngrd.classList.remove("active")
            btnlwn.classList.remove("active")
            btnplt.classList.add('active')
            for(let i = 0; i < lawn.length; i++)
                lawn[i].classList.toggle('blur')
            for(let i = 0; i < planting.length; i++)
                planting[i].classList.remove('blur')
            for(let i = 0; i < gaden.length; i++)
                gaden[i].classList.toggle('blur')
            btnplt.style.color = "#fff"
            btnplt.style.background = "#e06733"
            btngrd.style.color = ""
            btngrd.style.background = ""
            btnlwn.style.color = ""
            btnlwn.style.background = ""
        }
        else{
            btnplt.classList.add('active')
            for(let i = 0; i < planting.length; i++)
                planting[i].classList.remove('blur')
            btnplt.style.color = "#fff"
            btnplt.style.background = "#e06733"
            for(let i = 0; i < lawn.length; i++){
                if(btnlwn.classList.contains("active"))
                    lawn[i].classList.remove('blur')
                else
                    lawn[i].classList.add('blur')
            }
            for(let i = 0; i < gaden.length; i++){
                if(btngrd.classList.contains("active"))
                    gaden[i].classList.remove('blur')
                else
                    gaden[i].classList.add('blur')
            }
        }
    })

    if(burger){
    burger.addEventListener("click", function ()  {
        navbar.classList.toggle('active');
        burger.classList.toggle('active');
        });
    }
    if(out){
        out.addEventListener("click", function () {
            navbar.classList.remove('active');
            burger.classList.remove('active');
            
        })
    }
    for (let i = 0; i < home.length; i++) {
        home[i].addEventListener("click", function () {
            navbar.classList.remove('active');
            burger.classList.remove('active');
        })
    }
console.log(
    "При нажатии на кнопки:Gardens,Lawn,Planting происходит смена фокуса на услугах в разделе service +50"
    )
