import playList from "./playlist.js";

console.log(playList)


let num;
let isplay = false;
let playnum = 0;

const person = document.querySelector('.name');

const time = document.querySelector('.time');
const date = document.querySelector('.date');

const gretting = document.querySelector('.greeting');

const body = document.querySelector('body');

const city = document.querySelector('.city');


const slidenext = document.querySelector('.slide-next.slider-icon');
const slideprev = document.querySelector('.slide-prev.slider-icon');

slidenext.addEventListener('click', function() {
    if(num<20)
    num++;
    else num=1;
    setbg();
});

slideprev.addEventListener('click', function() {
    if(num>1)
    num--;
    else num=20;
    setbg();
});

function getRandomInt(max) {
    let min = 1;
    num= Math.floor(Math.random() * (max - min) + min);
  }


function getTimeOfDay() {
    const datee = new Date();
    const hours = datee.getHours();
    if (hours>=6 && hours < 12) {
        return 'morning';
    } else if (hours>=12 && hours < 18) {
        return 'afternoon';
    } else if(hours>=18 && hours < 24) {
        return 'evening';
    } else {
        return 'night';
    }
}

function setbg() {
let daytime=getTimeOfDay();
getRandomInt(21);
if(num<10) 
    num = '0' + num;
num.toString();
const img = new Image();
img.src = `https://raw.githubusercontent.com/rolling-scopes-school/stage1-tasks/assets/images/${daytime}/${num}.jpg`;
img.onload = () => {      
    body.style.backgroundImage = `url('https://raw.githubusercontent.com/rolling-scopes-school/stage1-tasks/assets/images/${daytime}/${num}.jpg')`;
  }; 
}
setbg();

function showTime() {
    const datee = new Date();
    const currentTime = datee.toLocaleTimeString();
    const options = {weekday:'long', month: 'long', day: 'numeric'};
    const currentDate = datee.toLocaleDateString('en-US', options);
    time.textContent = currentTime;
    date.textContent = currentDate;
    const timeofday = getTimeOfDay();
    const greetingtext = `Good ${timeofday}`;
    gretting.textContent = greetingtext;
    setTimeout(showTime, 1000);
}
showTime();
function getLocalStorage() {
    if(localStorage.getItem('person')) {
      person.value = localStorage.getItem('person');
    }
    if(localStorage.getItem('city')) {
        city.value = localStorage.getItem('city');
        getWeather();
    }
}
window.addEventListener('load', getLocalStorage)
function setLocalStorage() {
    localStorage.setItem('person', person.value);
    localStorage.setItem('city', city.value);
}
window.addEventListener('beforeunload', setLocalStorage)

const weatherIcon = document.querySelector('.weather-icon.owf');
const temperature = document.querySelector('.temperature');
const weatherDescription = document.querySelector('.weather-description');
const wind = document.querySelector('.wind');
const humidity = document.querySelector('.humidity');

city.addEventListener('change', getWeather);


async function getWeather() {
    if(city.value === '') 
    city.value='Minsk';
    const url = `https://api.openweathermap.org/data/2.5/weather?q=${city.value}&lang=en&appid=5997081d7d9689a58ee2d064d981e13d&units=metric`;
    const res = await fetch(url);
    const data = await res.json(); 
    

    weatherIcon.classList.add(`owf-${data.weather[0].id}`);
    temperature.textContent = `${data.main.temp}°C`;
    weatherDescription.textContent = data.weather[0].description;
    wind.textContent = `Wind: ${data.wind.speed} m/s`;
    humidity.textContent = `Humidity: ${data.main.humidity}%`;
}
getWeather()

const quote = document.querySelector('.quote');
const author = document.querySelector('.author');
const changequote = document.querySelector('.change-quote');

async function getQuotes() {  
    const quotes = 'js/data.json';
    const res = await fetch(quotes);
    const data = await res.json(); 
    const random = Math.floor(Math.random() * data.length);
    quote.textContent = data[random].text;
    author.textContent = data[random].author;
}
getQuotes();

changequote.addEventListener('click', getQuotes);

const play = document.querySelector('.play.player-icon');
const audio = document.querySelector('audio');
const playnext = document.querySelector('.play-next.player-icon');
const playprev = document.querySelector('.play-prev.player-icon');

function playAudio() {
  audio.src = playList[playnum].src;
  audio.currentTime = 0;
  if(audio !== undefined)
  audio.play();
  isplay = true;
}
function pauseAudio() {
  audio.pause();
}

play.addEventListener('click', function() {
  if(isplay){
    pauseAudio();
    isplay = false;
    play.classList.remove('pause'); 
  }
  else{
    playAudio();
    play.classList.add('pause');
  }
})

const ppLayNext = () => {
    if(playnum<3)
    playnum++;
    else playnum=0;
    playAudio();
}
const pplayPrev = () => {
    if(playnum>0)
    playnum--;
    else playnum=3;
    playAudio();
}

playnext.addEventListener('click', ppLayNext);
playprev.addEventListener('click', pplayPrev);